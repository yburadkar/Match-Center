package com.yb.uadnd.matchcentre.domain.repos

import com.yb.uadnd.matchcentre.domain.models.MatchCommentary
import io.reactivex.Maybe

interface CachedCommentaryDataSource {
    fun getMatchCommentary(matchId: Int): Maybe<MatchCommentary>
    fun saveMatchCommentary(matchCommentary: MatchCommentary)
    fun deleteMatchCommentary(matchId: Int): Maybe<Int>
}