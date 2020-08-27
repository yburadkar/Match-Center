package com.yb.uadnd.matchcentre.data

import androidx.lifecycle.LiveData
import com.yb.uadnd.matchcentre.data.local.DbComment
import com.yb.uadnd.matchcentre.data.local.DbCommMatchInfo
import com.yb.uadnd.matchcentre.data.remote.Match
import io.reactivex.Single

interface MatchRepository {
    fun getMatchCommentary(newMatchId: Int): LiveData<List<DbComment>>
    fun fetchMatch(matchId: String): Single<Match>
    fun getMatchInfo(matchId: String): LiveData<DbCommMatchInfo>
    fun getMatchList(): List<Int>
}