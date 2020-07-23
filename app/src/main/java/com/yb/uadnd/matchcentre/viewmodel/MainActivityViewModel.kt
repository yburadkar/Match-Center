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
    private val movieDb: MatchCentreDatabase
) : ViewModel() {

    private var _match = MutableLiveData<Match>()
    private var match: LiveData<Match> = _match
    private var _comments = MutableLiveData<List<Comment>>()
    private var comments: LiveData<List<Comment>> = _comments
    private var _matchInfo = MutableLiveData<MatchInfo>()
    private var matchInfo: LiveData<MatchInfo> = _matchInfo
    private var disposables = CompositeDisposable()

    fun setMatch(matchId: Int) {
        val matchIdText = matchId.toString()
        match = fetchMatch(matchIdText)

        //Fetch match commentary and store in local Db, to demonstrate basic caching
        matchRepo.deleteOldMatchComments(matchIdText)
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
                        movieDb.matchInfoDao.insertMatchInfo(info)
                            .subscribe()
                        it.commentaryEntries?.let{ entries ->
                            entries.forEach { entry ->
                                movieDb.commentDao.insertComment(Comment(matchId, entry))
                                    .subscribe()
                            }
                        }
                    }
                }
            )
            .addTo(disposables)

        comments = matchRepo.getMatchComments(matchIdText)
        matchInfo = matchRepo.getMatchInfo(matchIdText)
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

    fun getComments(): LiveData<List<Comment>>{
        return comments
    }

    fun getMatch(): LiveData<Match> {
        return match
    }

    fun getMatchInfo(): LiveData<MatchInfo>{
        return matchInfo
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