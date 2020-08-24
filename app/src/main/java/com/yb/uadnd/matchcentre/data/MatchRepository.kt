package com.yb.uadnd.matchcentre.data

import androidx.lifecycle.LiveData
import com.yb.uadnd.matchcentre.data.local.Comment
import com.yb.uadnd.matchcentre.data.local.MatchInfo
import com.yb.uadnd.matchcentre.data.remote.Match
import io.reactivex.Single

interface MatchRepository {
    fun getMatchCommentary(newMatchId: Int): LiveData<List<Comment>>
    fun fetchMatch(matchId: String): Single<Match>
    fun getMatchInfo(matchId: String): LiveData<MatchInfo>
    fun getMatchList(): List<Int>
}