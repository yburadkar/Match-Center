package com.yb.uadnd.matchcentre.data.repos

import com.yb.uadnd.matchcentre.data.remote.MatchService
import com.yb.uadnd.matchcentre.data.remote.MatchesDataSource
import com.yb.uadnd.matchcentre.domain.models.Match
import com.yb.uadnd.matchcentre.domain.repos.MatchRepository
import io.reactivex.Single
import javax.inject.Inject

class AppMatchRepository @Inject constructor(
    private val matchService: MatchService,
    private val mSource: MatchesDataSource,
) : MatchRepository {

    override fun fetchMatch(matchId: String): Single<Match> = matchService.getMatch(matchId).map { it }

    override fun getMatchList(): List<Int> = mSource.getMatchList()

}