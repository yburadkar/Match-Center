package com.yb.uadnd.matchcentre.domain.repos

import com.yb.uadnd.matchcentre.domain.models.Match
import io.reactivex.Single

interface MatchRepository {
    fun fetchMatch(matchId: String): Single<Match>
    fun getMatchList(): List<Int>
}