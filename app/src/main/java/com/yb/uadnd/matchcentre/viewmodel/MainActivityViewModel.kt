package com.yb.uadnd.matchcentre.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.yb.uadnd.matchcentre.AppRepository
import com.yb.uadnd.matchcentre.model.Commentary
import com.yb.uadnd.matchcentre.model.Commentary.Data.CommentaryEntry
import com.yb.uadnd.matchcentre.model.Match
import java.lang.Exception

class MainActivityViewModel(matchId: Int) : ViewModel() {
    private var mRespository: AppRepository
    private lateinit var mMatch: LiveData<Match>
    private lateinit var mCommentary: MutableLiveData<Commentary>
    private lateinit var mCommentaryList: LiveData<List<CommentaryEntry>>
    init {
        mRespository = AppRepository.getInstance()
        mMatch = mRespository.fetchMatch(matchId.toString())
        mCommentary = mRespository.fetchMatchCommentary(matchId.toString()) as MutableLiveData<Commentary>
        mCommentaryList = Transformations.map(mCommentary){
            input: Commentary? -> input?.data?.commentaryEntries
        }
    }

    fun getCommentaryList(): LiveData<List<CommentaryEntry>> {
        return mCommentaryList
    }

}