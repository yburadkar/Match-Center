package com.yb.uadnd.matchcentre.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yb.uadnd.matchcentre.domain.models.Match
import com.yb.uadnd.matchcentre.domain.models.MatchCommentary
import com.yb.uadnd.matchcentre.domain.models.MatchData
import com.yb.uadnd.matchcentre.domain.models.MatchEvent
import com.yb.uadnd.matchcentre.domain.repos.MatchCommentaryRepository
import com.yb.uadnd.matchcentre.domain.repos.MatchRepository
import com.yb.uadnd.matchcentre.helpers.DisposingViewModel
import com.yb.uadnd.matchcentre.helpers.Resource
import com.yb.uadnd.matchcentre.helpers.SimpleIdlingResource
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class MainActivityViewModel @Inject constructor(
    private val matchRepo: MatchRepository,
    private val matchCommentaryRepo: MatchCommentaryRepository,
    @Named("io") private val io: Scheduler,
    @Named("ui") private val ui: Scheduler,
    private val idlingRes: SimpleIdlingResource
) : DisposingViewModel() {

    private val _match = MutableLiveData<Resource<Match>>()
    val match: LiveData<Resource<Match>> = _match

    private val _matchCommentary = MutableLiveData<Resource<MatchCommentary>>()
    val matchCommentary: LiveData<Resource<MatchCommentary>> = _matchCommentary

    private val matchIds: List<Int> by lazy { matchRepo.getMatchList() }
    private var matchIndex = -1
    private var currentMatchId = 0

    init {
        loadNextMatch()
    }

    private fun loadMatch(newMatchId: Int) {
        currentMatchId = newMatchId
        fetchMatchCommentary(newMatchId)
        fetchMatch(newMatchId)
    }

    private fun fetchMatchCommentary(matchId: Int) {
        matchCommentaryRepo.getMatchCommentary(matchId)
            .subscribeOn(io)
            .observeOn(ui)
            .doOnSubscribe { _matchCommentary.postValue(Resource.loading(data = null)) }
            .subscribeBy(
                onError = {
                    _matchCommentary.value = Resource.error(data = null, error = it)
                },
                onSuccess = {
                    _matchCommentary.value = Resource.success(data = it)
                }
            ).addTo(disposables)
    }

    fun loadNextMatch() = loadMatch(nextMatchId())

    fun loadPrevMatch() = loadMatch(prevMatchId())

    fun reloadMatch() {
        fetchMatch(currentMatchId)
    }

    private fun nextMatchId(): Int {
        matchIndex++
        if (matchIndex == matchIds.size) matchIndex = 0
        return matchIds[matchIndex]
    }

    private fun prevMatchId(): Int {
        if (matchIndex == 0) matchIndex = matchIds.size - 1
        else matchIndex--
        return matchIds[matchIndex]
    }

    private fun fetchMatch(matchId: Int) {
        matchRepo.fetchMatch(matchId = matchId.toString())
            .subscribeOn(io)
            .observeOn(ui)
            .doOnSubscribe {
                idlingRes.setIdleState(false)
                _match.value = Resource.loading(data = _match.value?.data)
            }
            .subscribeBy(
                onError = {
                    Timber.e(it)
                    _match.value = Resource.error(data = null, error = it)
                    idlingRes.setIdleState(true)
                },
                onSuccess = {
                    _match.value = Resource.success(updateMatchEvents(it))
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

}