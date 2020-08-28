package com.yb.uadnd.matchcentre.domain

import androidx.lifecycle.LiveData
import io.reactivex.Single

interface MatchRepository {
    fun getMatchCommentary(newMatchId: Int): LiveData<List<Comment>>
    fun fetchMatch(matchId: String): Single<Match>
    fun getMatchInfo(matchId: String): LiveData<CommentaryMatchInfo>
    fun getMatchList(): List<Int>
}