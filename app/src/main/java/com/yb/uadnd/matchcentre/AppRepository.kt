package com.yb.uadnd.matchcentre

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yb.uadnd.matchcentre.data.Event
import com.yb.uadnd.matchcentre.data.Match
import com.yb.uadnd.matchcentre.data.MatchData
import com.yb.uadnd.matchcentre.data.MatchService
import com.yb.uadnd.matchcentre.data.local.Comment
import com.yb.uadnd.matchcentre.data.local.MatchCentreDatabase
import com.yb.uadnd.matchcentre.data.local.MatchInfo
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

class AppRepository @Inject constructor(
    private var matchService: MatchService,
    private val db: MatchCentreDatabase,
    private val io: Scheduler,
    private val ui: Scheduler,
    private val idlingResource: SimpleIdlingResource
) {
    private val refreshIntervalSeconds = 3600  // 1 hour
    private val disposables = CompositeDisposable()

    fun getMatchCommentary(newMatchId: Int): LiveData<List<Comment>> {
        var lastRefresh: Long = 0
        db.matchInfoDao.getLastRefreshTime(newMatchId)
            .subscribeOn(io)
            .observeOn(ui)
            .subscribeBy(
                onError = { Timber.e(it)},
                onSuccess = {
                    if (it.isNotEmpty()) lastRefresh = it.first()
                    if(System.currentTimeMillis()/1000 - lastRefresh > refreshIntervalSeconds)
                        refreshCommentary(newMatchId)
                }
            ).addTo(disposables)

        return db.commentDao.getAllMatchComments(newMatchId)
    }

    private fun refreshCommentary(newMatchId: Int) {
        idlingResource.setIdleState(false)
        Timber.d("Fetching comments for match $newMatchId")
        matchService.getMatchCommentary(newMatchId.toString())
            .subscribeOn(io)
            .observeOn(io)
            .subscribeBy(
                onSuccess = { commentary ->
                    idlingResource.setIdleState(true)
                    db.commentDao.deleteAllMatchComments(newMatchId)
                    commentary?.data?.let{
                        val info = MatchInfo.from(data = it)
                        db.matchInfoDao.insertMatchInfo(info)
                            .subscribe()
                        it.commentaryEntries?.let{ entries ->
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

    fun fetchMatch(matchId: String): LiveData<Match> {
        idlingResource.setIdleState(false)
        val match = MutableLiveData<Match>()
        matchService.getMatch(matchId)
            .subscribeOn(io)
            .observeOn(ui)
            .subscribeBy(
                onSuccess = {
                    match.value = updateMatchEvents(it)
                    idlingResource.setIdleState(true)
                },
                onError = {
                    idlingResource.setIdleState(true)
                    Timber.e(it)
                }
            ).addTo(disposables)
        return match
    }

    private fun updateMatchEvents(match: Match): Match {
        match.data?.also { data ->
            data.events?.forEach {
                it.updateImageUrl(getEventTeamUrl(data, it))
            }
        }
        return match
    }

    private fun getEventTeamUrl(data: MatchData, event: Event): String? {
        val homeTeamId = data.homeTeam?.id
        val awayTeamId = data.awayTeam?.id
        return when(event.teamId) {
            homeTeamId -> data.homeTeam?.imageUrl
            awayTeamId -> data.awayTeam?.imageUrl
            else -> null
        }
    }

    fun getMatchInfo(matchId: String): LiveData<MatchInfo>{
        return db.matchInfoDao.getMatchInfo(matchId.toInt())
    }

    companion object {
        private var instance: AppRepository? = null

        fun getInstance(
            service: MatchService,
            roomDatabase: MatchCentreDatabase,
            io: Scheduler,
            ui: Scheduler,
            res: SimpleIdlingResource
        ): AppRepository {
            if(instance == null) {
                instance = AppRepository(service, roomDatabase, io, ui, res)
            }
            return instance as AppRepository
        }
    }
}