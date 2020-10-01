package com.yb.uadnd.matchcentre.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yb.uadnd.matchcentre.data.remote.models.ApiCommentaryData
import com.yb.uadnd.matchcentre.domain.CommentaryMatchInfo

@Entity(tableName = "matchInfo")
class DbCommentaryMatchInfo(
    @PrimaryKey(autoGenerate = false)
    override val matchId: Int = 0,
    override val homeTeamName: String? = null,
    override val homeTeamId: String? = null,
    override val homeScore: Int = 0,
    override val awayTeamName: String? = null,
    override val awayTeamId: String? = null,
    override val awayScore: Int = 0,
    override val competitionId: Int = 0,
    override val competition: String? = null,
    override val homeTeamImageUrl: String? = null,
    override val awayTeamImageUrl: String? = null,
    override val lastRefreshed: Long = System.currentTimeMillis() / 1000
) : CommentaryMatchInfo {

    companion object {
        fun from(data: ApiCommentaryData): DbCommentaryMatchInfo {
            with(data) {
                return DbCommentaryMatchInfo(feedMatchId, homeTeamName, homeTeamId, homeScore, awayTeamName, awayTeamId, awayScore, competitionId, competition, homeTeamImageUrl, awayTeamImageUrl)
            }
        }
    }
}