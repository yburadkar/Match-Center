package com.yb.uadnd.matchcentre.data.local

import com.yb.uadnd.matchcentre.data.local.models.DbComment
import com.yb.uadnd.matchcentre.data.local.models.DbMatchCommentary
import com.yb.uadnd.matchcentre.data.local.models.DbMatchInfo
import com.yb.uadnd.matchcentre.domain.models.MatchCommentary
import com.yb.uadnd.matchcentre.domain.repos.CommentaryDataSource
import io.reactivex.Completable
import io.reactivex.Single

class LocalCommentaryDataSource(
    private val matchInfoDao: MatchInfoDao
) : CommentaryDataSource {

    override fun getMatchCommentary(matchId: Int): Single<MatchCommentary> {
        return matchInfoDao.getMatchInfoWithComments(matchId).map { DbMatchCommentary(it) }
    }

    fun saveMatchCommentary(matchCommentary: MatchCommentary) {
        matchInfoDao.insertMatchInfoWithComments(
            matchInfo = DbMatchInfo(matchCommentary),
            comments = with(matchCommentary) {
                commentaryEntries?.map {
                    DbComment(matchId = feedMatchId, comment = it)
                } ?: emptyList()
            }
        )
    }

    fun deleteMatchCommentary(matchId: Int): Completable {
        return matchInfoDao.deleteMatchInfo(matchId)
    }

}