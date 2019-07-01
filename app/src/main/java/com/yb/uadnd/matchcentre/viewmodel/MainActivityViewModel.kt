package com.yb.uadnd.matchcentre.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.yb.uadnd.matchcentre.AppRepository
import com.yb.uadnd.matchcentre.MyApp
import com.yb.uadnd.matchcentre.model.Match
import com.yb.uadnd.matchcentre.model.database.Comment
import com.yb.uadnd.matchcentre.model.database.MatchInfo

class MainActivityViewModel(application: Application, matchId: Int) : AndroidViewModel(application) {
    private var mRepository: AppRepository
    private var mMatch: LiveData<Match>
    private var mComments: LiveData<List<Comment>>
    private var mInfo: LiveData<MatchInfo>

    private var mApp: MyApp = application as MyApp

    init {
        mRepository = mApp.getRepository()

        val matchIdText = matchId.toString()
        mMatch = mRepository.fetchMatch(matchIdText)

        //Fetch match commentary and store in local Db, to demonstrate basic caching
        mRepository.deleteOldMatchComments(matchIdText)
        mRepository.fetchMatchCommentaryAndCacheInDb(matchIdText)
        mComments = mRepository.getMatchComments(matchIdText)
        mInfo = mRepository.getMatchInfo(matchIdText)
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
}