package com.yb.uadnd.matchcentre.data.repo

import androidx.lifecycle.LiveData
import com.yb.uadnd.matchcentre.SimpleIdlingResource
import com.yb.uadnd.matchcentre.data.MatchRepository
import com.yb.uadnd.matchcentre.data.remote.Match
import com.yb.uadnd.matchcentre.data.remote.MatchService
import com.yb.uadnd.matchcentre.data.local.Comment
import com.yb.uadnd.matchcentre.data.local.MatchCentreDatabase
import com.yb.uadnd.matchcentre.data.local.MatchInfo
import com.yb.uadnd.matchcentre.data.remote.CommentaryService
import com.yb.uadnd.matchcentre.data.remote.MatchesDataSource
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

class AppMatchRepository @Inject constructor(
    private val matchService: MatchService,
    private val commentaryService: CommentaryService,
    private val db: MatchCentreDatabase,
    private val mSource: MatchesDataSource,
    private val io: Scheduler,
    private val ui: Scheduler,
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

        return db.commentDao.getAllMatchComments(newMatchId)
    }

    override fun fetchMatch(matchId: String): Single<Match> = matchService.getMatch(matchId)

    override fun getMatchInfo(matchId: String): LiveData<MatchInfo> = db.matchInfoDao.getMatchInfo(matchId.toInt())

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
                        val info = MatchInfo.from(data = it)
                        db.matchInfoDao.insertMatchInfo(info)
                            .subscribe()
                        it.commentaryEntries?.let { entries ->
                            entries.forEach { entry ->
                                db.commentDao.insertComment(Comment(newMatchId, entry))
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