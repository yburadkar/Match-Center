package com.yb.uadnd.matchcentre.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yb.uadnd.matchcentre.Resource
import com.yb.uadnd.matchcentre.SimpleIdlingResource
import com.yb.uadnd.matchcentre.domain.Comment
import com.yb.uadnd.matchcentre.domain.CommentaryMatchInfo
import com.yb.uadnd.matchcentre.domain.Match
import com.yb.uadnd.matchcentre.domain.MatchData
import com.yb.uadnd.matchcentre.domain.MatchEvent
import com.yb.uadnd.matchcentre.domain.MatchRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class MainActivityViewModel(
    private val matchRepo: MatchRepository,
    private val io: Scheduler,
    private val ui: Scheduler,
    private val idlingRes: SimpleIdlingResource
) : ViewModel() {

    private val match = MutableLiveData<Resource<Match>>()
    private lateinit var comments: LiveData<List<Comment>>
    private lateinit var matchInfo: LiveData<CommentaryMatchInfo>
    private var disposables = CompositeDisposable()
    private val matches: List<Int> by lazy { matchRepo.getMatchList() }  //using hardcoded match Ids for sample app
    private var matchIndex = -1
    private var currentMatchId = 0

    private fun loadMatch(newMatchId: Int) {
        currentMatchId = newMatchId
        comments = matchRepo.getMatchCommentary(newMatchId)
        matchInfo = matchRepo.getMatchInfo(newMatchId.toString())
        fetchMatch(newMatchId)
    }

    fun getComments(): LiveData<List<Comment>> = comments

    fun getMatch(): LiveData<Resource<Match>> = match

    fun getMatchInfo(): LiveData<CommentaryMatchInfo> = matchInfo

    fun loadNextMatch() = loadMatch(getNextMatchId())

    fun loadPrevMatch() = loadMatch(getPreviousMatchId())

    private fun fetchMatch(matchId: Int) {
        matchRepo.fetchMatch(matchId = matchId.toString())
            .subscribeOn(io)
            .observeOn(ui)
            .doOnSubscribe {
                idlingRes.setIdleState(false)
                match.value = Resource.loading(data = match.value?.data)
            }
            .subscribeBy(
                onError = {
                    Timber.e(it)
                    match.value = Resource.error(data = null, error = it)
                    idlingRes.setIdleState(true)
                },
                onSuccess = {
                    match.value = Resource.success(updateMatchEvents(it))
                    idlingRes.setIdleState(true)
                }

            ).addTo(disposables)
    }

    private fun updateMatchEvents(match: Match): Match {
        match.data?.also { data ->
            data.events?.forEach {
                it.updateImageUrl(getEventTeamUrl(data, it))
            }
        }
        return match
    }

    private fun getEventTeamUrl(data: MatchData, event: MatchEvent): String? {
        val homeTeamId = data.homeTeam?.id
        val awayTeamId = data.awayTeam?.id
        return when (event.teamId) {
            homeTeamId -> data.homeTeam?.imageUrl
            awayTeamId -> data.awayTeam?.imageUrl
            else -> null
        }
    }

    fun reloadMatch() {
        fetchMatch(currentMatchId)
    }

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

class MainActivityViewModelFactory @Inject constructor(
    private val matchRepo: MatchRepository,
    @Named("io") private val io: Scheduler,
    @Named("ui") private val ui: Scheduler,
    private val idlingRes: SimpleIdlingResource
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainActivityViewModel(matchRepo, io, ui, idlingRes) as T

}