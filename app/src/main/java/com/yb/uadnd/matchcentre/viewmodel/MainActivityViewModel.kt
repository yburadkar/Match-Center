package com.yb.uadnd.matchcentre.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.yb.uadnd.matchcentre.AppRepository
import com.yb.uadnd.matchcentre.model.Commentary
import com.yb.uadnd.matchcentre.model.Match

class MainActivityViewModel(application: Application, matchId: Int) : AndroidViewModel(application) {
    private var mRespository: AppRepository
    private lateinit var mMatch: LiveData<Match>
    private lateinit var mCommentary: LiveData<Commentary>
    init {
        mRespository = AppRepository.getInstance()
        mMatch = mRespository.fetchMatch(matchId.toString())
        mCommentary = mRespository.fetchMatchCommentary(matchId.toString())
    }
}