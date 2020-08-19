package com.yb.uadnd.matchcentre.data

import androidx.lifecycle.LiveData
import com.yb.uadnd.matchcentre.data.local.Comment
import com.yb.uadnd.matchcentre.data.local.MatchInfo
import com.yb.uadnd.matchcentre.data.remote.Match

interface MatchRepository {
    fun getMatchCommentary(newMatchId: Int): LiveData<List<Comment>>
    fun fetchMatch(matchId: String): LiveData<Match>
    fun getMatchInfo(matchId: String): LiveData<MatchInfo>
    fun getMatchList(): List<Int>
}