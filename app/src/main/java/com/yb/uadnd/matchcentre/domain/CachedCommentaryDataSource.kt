package com.yb.uadnd.matchcentre.domain

import com.yb.uadnd.matchcentre.domain.models.MatchCommentary
import com.yb.uadnd.matchcentre.domain.repos.CommentaryDataSource
import io.reactivex.Completable
import io.reactivex.Single

interface CachedCommentaryDataSource : CommentaryDataSource {
    override fun getMatchCommentary(matchId: Int): Single<MatchCommentary>
    fun saveMatchCommentary(matchCommentary: MatchCommentary)
    fun deleteMatchCommentary(matchId: Int): Completable
}