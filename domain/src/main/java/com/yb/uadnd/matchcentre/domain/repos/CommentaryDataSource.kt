package com.yb.uadnd.matchcentre.domain.repos

import com.yb.uadnd.matchcentre.domain.models.MatchCommentary
import io.reactivex.Single

interface CommentaryDataSource {
    fun getMatchCommentary(matchId: Int): Single<MatchCommentary>
}