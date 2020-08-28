package com.yb.uadnd.matchcentre.domain

import androidx.lifecycle.LiveData
import com.yb.uadnd.matchcentre.data.local.DbComment
import com.yb.uadnd.matchcentre.data.local.DbCommentaryMatchInfo
import com.yb.uadnd.matchcentre.data.remote.ApiMatch
import io.reactivex.Single

interface MatchRepository {
    fun getMatchCommentary(newMatchId: Int): LiveData<List<DbComment>>
    fun fetchMatch(matchId: String): Single<ApiMatch>
    fun getMatchInfo(matchId: String): LiveData<DbCommentaryMatchInfo>
    fun getMatchList(): List<Int>
}