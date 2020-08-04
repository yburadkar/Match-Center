package com.yb.uadnd.matchcentre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yb.uadnd.matchcentre.AppRepository
import com.yb.uadnd.matchcentre.model.Match
import com.yb.uadnd.matchcentre.model.database.Comment
import com.yb.uadnd.matchcentre.model.database.MatchInfo
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(
    private val matchRepo: AppRepository
) : ViewModel() {

    private lateinit var match: LiveData<Match>
    private lateinit var comments: LiveData<List<Comment>>
    private lateinit var matchInfo: LiveData<MatchInfo>
    private var disposables = CompositeDisposable()
    private val matches: List<Int> = listOf(987597, 987598, 987599)  //using hardcoded match Ids for sample app
    private var matchIndex = -1
    private var prevMatchId = -1

    fun loadMatch(newMatchId: Int) {
        match = fetchMatch(newMatchId.toString())
        if(prevMatchId == -1) prevMatchId = newMatchId
        comments = matchRepo.getMatchCommentary(newMatchId)
        matchInfo = matchRepo.getMatchInfo(newMatchId.toString())
        prevMatchId = newMatchId
    }

    private fun fetchMatch(matchId: String): LiveData<Match> {
        return matchRepo.fetchMatch(matchId)
    }

    fun getComments(): LiveData<List<Comment>> = comments

    fun getMatch(): LiveData<Match> = match

    fun getMatchInfo(): LiveData<MatchInfo> = matchInfo

    fun getNextMatchId(): Int {
        matchIndex++
        if(matchIndex == matches.size) matchIndex = 0
        return matches[matchIndex]
    }

    fun getPreviousMatchId(): Int {
        if(matchIndex == 0) matchIndex = matches.size - 1
        else matchIndex--
        return matches[matchIndex]
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}

class MainActivityViewModelFactory(
    private val matchRepo: AppRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(matchRepo) as T
    }

}