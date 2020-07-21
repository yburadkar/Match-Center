package com.yb.uadnd.matchcentre.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yb.uadnd.matchcentre.AppRepository
import com.yb.uadnd.matchcentre.MyApp
import com.yb.uadnd.matchcentre.model.Match
import com.yb.uadnd.matchcentre.model.database.Comment
import com.yb.uadnd.matchcentre.model.database.MovieDatabase
import com.yb.uadnd.matchcentre.model.database.MatchInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivityViewModel(application: Application, matchId: Int) : AndroidViewModel(application) {
    private var mRepository: AppRepository
    private var mDb: MovieDatabase
    private var mMatch: LiveData<Match>
    private var mComments: LiveData<List<Comment>>
    private var mInfo: LiveData<MatchInfo>
    private var disposables = CompositeDisposable()

    private var mApp: MyApp = application as MyApp

    init {
        mRepository = mApp.getRepository()
        mDb = mApp.getDatabase()
        val matchIdText = matchId.toString()
        mMatch = fetchMatch(matchIdText)

        //Fetch match commentary and store in local Db, to demonstrate basic caching
        mRepository.deleteOldMatchComments(matchIdText)
                .subscribeOn(Schedulers.io())
                .subscribe()
                .addTo(disposables)

        mRepository.fetchMatchCommentaryAndCacheInDb(matchIdText)
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
                            mDb.matchInfoDao.insertMatchInfo(info)
                                .subscribe()
                            it.commentaryEntries?.let{ entries ->
                                entries.forEach { entry ->
                                    mDb.commentDao.insertComment(Comment(matchId, entry))
                                        .subscribe()
                                }
                            }
                        }
                    }
                )
                .addTo(disposables)

        mComments = mRepository.getMatchComments(matchIdText)
        mInfo = mRepository.getMatchInfo(matchIdText)
    }

    private fun fetchMatch(matchId: String): LiveData<Match> {
        val match = MutableLiveData<Match>()
        mRepository.fetchMatch(matchId)
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
        return mComments
    }

    fun getMatch(): LiveData<Match> {
        return mMatch
    }

    fun getMatchInfo(): LiveData<MatchInfo>{
        return mInfo
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}