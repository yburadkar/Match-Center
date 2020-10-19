package com.yb.uadnd.matchcentre.data.local.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.yb.uadnd.matchcentre.data.remote.models.ApiCommentaryData
import com.yb.uadnd.matchcentre.domain.models.CommentaryMatchInfo
import com.yb.uadnd.matchcentre.domain.models.MatchCommentary

@Entity(tableName = "matchInfo")
class DbMatchInfo(
    @PrimaryKey(autoGenerate = false)
    override val matchId: Int = 0,
    override val homeTeamName: String? = null,
    override val homeScore: Int = 0,
    override val awayTeamName: String? = null,
    override val awayScore: Int = 0,
    override val competition: String? = null,
    override val homeTeamImageUrl: String? = null,
    override val awayTeamImageUrl: String? = null,
    override val lastRefreshed: Long = System.currentTimeMillis() / 1000
) : CommentaryMatchInfo {

    @Ignore
    constructor(matchCommentary: MatchCommentary) : this(
        matchId = matchCommentary.feedMatchId,
        homeTeamName = matchCommentary.homeTeamName,
        homeScore = matchCommentary.homeScore,
        awayTeamName = matchCommentary.awayTeamName,
        awayScore = matchCommentary.awayScore,
        competition = matchCommentary.competition,
        homeTeamImageUrl = matchCommentary.homeTeamImageUrl,
        awayTeamImageUrl = matchCommentary.awayTeamImageUrl,
        lastRefreshed = matchCommentary.lastRefreshed,
    )

    companion object {
        fun from(data: ApiCommentaryData): DbMatchInfo {
            with(data) {
                return DbMatchInfo(feedMatchId, homeTeamName, homeScore, awayTeamName, awayScore, competition, homeTeamImageUrl, awayTeamImageUrl)
            }
        }
    }
}