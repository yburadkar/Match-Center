package com.yb.uadnd.matchcentre.domain

import io.reactivex.Single

interface MatchRepository {
    fun fetchMatch(matchId: String): Single<Match>
    fun getMatchList(): List<Int>
}