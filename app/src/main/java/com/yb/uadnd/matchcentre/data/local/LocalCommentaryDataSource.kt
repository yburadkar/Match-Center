package com.yb.uadnd.matchcentre.data.local

import com.yb.uadnd.matchcentre.data.local.models.DbComment
import com.yb.uadnd.matchcentre.data.local.models.DbMatchCommentary
import com.yb.uadnd.matchcentre.data.local.models.DbMatchInfo
import com.yb.uadnd.matchcentre.domain.models.MatchCommentary
import com.yb.uadnd.matchcentre.domain.repos.CachedCommentaryDataSource
import io.reactivex.Maybe
import javax.inject.Inject

class LocalCommentaryDataSource @Inject constructor(
    private val matchInfoDao: MatchInfoDao
) : CachedCommentaryDataSource {

    override fun getMatchCommentary(matchId: Int): Maybe<MatchCommentary> {
        return matchInfoDao.getMatchInfoWithComments(matchId).map { DbMatchCommentary(it) }
    }

    override fun saveMatchCommentary(matchCommentary: MatchCommentary) {
        matchInfoDao.insertMatchInfoWithComments(
            matchInfo = DbMatchInfo(matchCommentary),
            comments = with(matchCommentary) {
                commentaryEntries?.map {
                    DbComment(matchId = feedMatchId, comment = it)
                } ?: emptyList()
            }
        )
    }

    override fun deleteMatchCommentary(matchId: Int): Maybe<Int> {
        return matchInfoDao.deleteMatchInfo(matchId)
    }

}