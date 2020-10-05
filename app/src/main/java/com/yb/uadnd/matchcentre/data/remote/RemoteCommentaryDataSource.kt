package com.yb.uadnd.matchcentre.data.remote

import com.yb.uadnd.matchcentre.data.remote.models.RemoteMatchCommentary
import com.yb.uadnd.matchcentre.domain.models.MatchCommentary
import com.yb.uadnd.matchcentre.domain.repos.CommentaryDataSource
import io.reactivex.Single

class RemoteCommentaryDataSource(
    private val commentaryService: CommentaryService
) : CommentaryDataSource {

    override fun getMatchCommentary(matchId: Int): Single<MatchCommentary> {
        return commentaryService.getMatchCommentary(matchId.toString())
            .map { RemoteMatchCommentary(it.data) }
    }

}