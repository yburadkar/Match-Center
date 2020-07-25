package com.yb.uadnd.matchcentre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yb.uadnd.matchcentre.AppRepository
import com.yb.uadnd.matchcentre.model.Match
import com.yb.uadnd.matchcentre.model.database.Comment
import com.yb.uadnd.matchcentre.model.database.MatchCentreDatabase
import com.yb.uadnd.matchcentre.model.database.MatchInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivityViewModel(
    private val matchRepo: AppRepository,
    private val matchDb: MatchCentreDatabase
) : ViewModel() {

    private lateinit var match: LiveData<Match>
    private lateinit var comments: LiveData<List<Comment>>
    private lateinit var matchInfo: LiveData<MatchInfo>
    private var disposables = CompositeDisposable()
    private val matches: List<Int> = listOf(987597, 987598, 987599)  //using hardcoded match Ids for sample app
    private var matchIndex = -1
    private var matchId = 0

    fun loadMatch(newMatchId: Int) {
        val matchIdText = newMatchId.toString()
        match = fetchMatch(matchIdText)
        reloadMatchCommentary(newMatchId)
        comments = matchRepo.getMatchComments(matchIdText)
        matchInfo = matchRepo.getMatchInfo(matchIdText)
    }

    private fun reloadMatchCommentary(newMatchId: Int) {
        val matchIdText = newMatchId.toString()
        //Fetch match commentary and store in local Db, to demonstrate basic caching
        matchRepo.deleteOldMatchComments(matchId.toString())
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(disposables)

        matchRepo.fetchMatchCommentaryAndCacheInDb(matchIdText)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onError = {
                    Timber.i("Failed to get match commentary")
                },
                onSuccess = { commentary ->
                    commentary?.data?.let{
                        val info = MatchInfo.from(data = it)
                        matchDb.matchInfoDao.insertMatchInfo(info)
                            .subscribe()
                        it.commentaryEntries?.let{ entries ->
                            entries.forEach { entry ->
                                matchDb.commentDao.insertComment(Comment(newMatchId, entry))
                                    .subscribe()
                            }
                        }
                    }
                }
            )
            .addTo(disposables)
    }

    private fun fetchMatch(matchId: String): LiveData<Match> {
        val match = MutableLiveData<Match>()
        matchRepo.fetchMatch(matchId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            Timber.i("Failed to fetch match")
                        },
                        onSuccess = {
                            match.value = mapMatch(it)
                        }
                ).addTo(disposables)
        return match
    }

    private fun mapMatch(match: Match): Match {
        match.data?.also { data ->
            data.events?.forEach {
                it.updateImageUrl(getEventTeamUrl(data, it))
            }
        }
        return match
    }

    private fun getEventTeamUrl(data: Match.Data, event: Match.Data.Event): String? {
        val homeTeamId = data.homeTeam?.id
        val awayTeamId = data.awayTeam?.id
        return when(event.teamId) {
            homeTeamId -> data.homeTeam?.imageUrl
            awayTeamId -> data.awayTeam?.imageUrl
            else -> null
        }
    }

    fun getComments(): LiveData<List<Comment>> = comments

    fun getMatch(): LiveData<Match> = match

    fun getMatchInfo(): LiveData<MatchInfo> = matchInfo

    fun getNextMatchId(): Int {
        matchIndex++
        if(matchIndex == matches.size) matchIndex = 0
        return  matches[matchIndex]
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}

class MainActivityViewModelFactory(
    private val matchRepo: AppRepository,
    private val matchDb: MatchCentreDatabase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(matchRepo, matchDb) as T
    }

}