package com.yb.uadnd.matchcentre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yb.uadnd.matchcentre.data.MatchRepository
import com.yb.uadnd.matchcentre.data.remote.Match
import com.yb.uadnd.matchcentre.data.local.Comment
import com.yb.uadnd.matchcentre.data.local.MatchInfo
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(
    private val matchRepo: MatchRepository
) : ViewModel() {

    private lateinit var match: LiveData<Match>
    private lateinit var comments: LiveData<List<Comment>>
    private lateinit var matchInfo: LiveData<MatchInfo>
    private var disposables = CompositeDisposable()
    private val matches: List<Int> by lazy { matchRepo.getMatchList() }  //using hardcoded match Ids for sample app
    private var matchIndex = -1

    private fun loadMatch(newMatchId: Int) {
        comments = matchRepo.getMatchCommentary(newMatchId)
        matchInfo = matchRepo.getMatchInfo(newMatchId.toString())
        match = matchRepo.fetchMatch(newMatchId.toString())
    }

    fun getComments(): LiveData<List<Comment>> = comments

    fun getMatch(): LiveData<Match> = match

    fun getMatchInfo(): LiveData<MatchInfo> = matchInfo

    fun loadNextMatch() = loadMatch(getNextMatchId())

    fun loadPrevMatch() = loadMatch(getPreviousMatchId())

    private fun getNextMatchId(): Int {
        matchIndex++
        if (matchIndex == matches.size) matchIndex = 0
        return matches[matchIndex]
    }

    private fun getPreviousMatchId(): Int {
        if (matchIndex == 0) matchIndex = matches.size - 1
        else matchIndex--
        return matches[matchIndex]
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}

class MainActivityViewModelFactory(
    private val matchRepo: MatchRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainActivityViewModel(matchRepo) as T

}