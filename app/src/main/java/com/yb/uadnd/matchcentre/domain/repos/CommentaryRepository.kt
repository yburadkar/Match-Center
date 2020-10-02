package com.yb.uadnd.matchcentre.domain.repos

import androidx.lifecycle.LiveData
import com.yb.uadnd.matchcentre.domain.Comment
import com.yb.uadnd.matchcentre.domain.CommentaryMatchInfo

interface CommentaryRepository {

    fun getMatchCommentary(newMatchId: Int): LiveData<List<Comment>>
    fun getMatchInfo(matchId: String): LiveData<CommentaryMatchInfo>

}