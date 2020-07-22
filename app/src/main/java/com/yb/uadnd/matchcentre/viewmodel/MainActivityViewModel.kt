package com.yb.uadnd.matchcentre.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yb.uadnd.matchcentre.AppRepository
import com.yb.uadnd.matchcentre.MyApp
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

class MainActivityViewModel(application: Application, matchId: Int) : AndroidViewModel(application) {
    private var matchRepo: AppRepository
    private var movieDb: MatchCentreDatabase
    private var match: LiveData<Match>
    private var comments: LiveData<List<Comment>>
    private var matchInfo: LiveData<MatchInfo>
    private var disposables = CompositeDisposable()

    private var mApp: MyApp = application as MyApp

    init {
        matchRepo = mApp.getMatchRepository()
        movieDb = mApp.getMatchDb()
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
                            val info = MatchInfo(it.id, matchId,
                                it.homeTeamName, it.homeTeamId, it.homeScore,
                                it.awayTeamName, it.awayTeamId, it.awayScore,
                                it.competitionId, it.competition)
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
                            match.value = it
                        }
                ).addTo(disposables)
        return match
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

class MainActivityViewModelFactory(private var application: Application, private var matchId: Int)
    : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(application, matchId) as T
    }

}