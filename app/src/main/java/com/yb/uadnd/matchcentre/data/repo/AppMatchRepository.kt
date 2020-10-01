package com.yb.uadnd.matchcentre.data.repo

import androidx.lifecycle.LiveData
import com.yb.uadnd.matchcentre.SimpleIdlingResource
import com.yb.uadnd.matchcentre.data.local.MatchCentreDatabase
import com.yb.uadnd.matchcentre.data.local.models.DbComment
import com.yb.uadnd.matchcentre.data.local.models.DbCommentaryMatchInfo
import com.yb.uadnd.matchcentre.data.remote.CommentaryService
import com.yb.uadnd.matchcentre.data.remote.MatchService
import com.yb.uadnd.matchcentre.data.remote.MatchesDataSource
import com.yb.uadnd.matchcentre.domain.Comment
import com.yb.uadnd.matchcentre.domain.CommentaryMatchInfo
import com.yb.uadnd.matchcentre.domain.Match
import com.yb.uadnd.matchcentre.domain.MatchRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class AppMatchRepository @Inject constructor(
    private val matchService: MatchService,
    private val commentaryService: CommentaryService,
    private val db: MatchCentreDatabase,
    private val mSource: MatchesDataSource,
    @Named("io") private val io: Scheduler,
    @Named("ui") private val ui: Scheduler,
    private val idlingResource: SimpleIdlingResource
) : MatchRepository {

    private val refreshIntervalSeconds = 3600  // 1 hour
    private val disposables = CompositeDisposable()

    override fun getMatchCommentary(newMatchId: Int): LiveData<List<Comment>> {
        var lastRefresh: Long = 0
        db.matchInfoDao.getLastRefreshTime(newMatchId)
            .subscribeOn(io)
            .observeOn(ui)
            .subscribeBy(
                onError = { Timber.e(it) },
                onSuccess = {
                    if (it.isNotEmpty()) lastRefresh = it.first()
                    if (System.currentTimeMillis() / 1000 - lastRefresh > refreshIntervalSeconds)
                        refreshCommentary(newMatchId)
                }
            ).addTo(disposables)

        return db.commentDao.getAllMatchComments(newMatchId) as LiveData<List<Comment>>
    }

    override fun getMatchInfo(matchId: String): LiveData<CommentaryMatchInfo> = db.matchInfoDao.getMatchInfo(matchId.toInt()) as LiveData<CommentaryMatchInfo>

    override fun fetchMatch(matchId: String): Single<Match> = matchService.getMatch(matchId).map { it }

    override fun getMatchList(): List<Int> = mSource.getMatchList()

    private fun refreshCommentary(newMatchId: Int) {
        idlingResource.setIdleState(false)
        Timber.d("Fetching comments for match $newMatchId")
        commentaryService.getMatchCommentary(newMatchId.toString())
            .subscribeOn(io)
            .observeOn(io)
            .subscribeBy(
                onSuccess = { commentary ->
                    idlingResource.setIdleState(true)
                    db.commentDao.deleteAllMatchComments(newMatchId)
                    commentary?.data?.let {
                        val info = DbCommentaryMatchInfo.from(data = it)
                        db.matchInfoDao.insertMatchInfo(info)
                            .subscribe()
                        it.commentaryEntries?.let { entries ->
                            entries.forEach { entry ->
                                db.commentDao.insertComment(DbComment(newMatchId, entry))
                            }
                        }
                    }
                },
                onError = {
                    idlingResource.setIdleState(true)
                    Timber.e(it)
                }
            ).addTo(disposables)
    }

}