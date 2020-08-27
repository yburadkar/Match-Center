package com.yb.uadnd.matchcentre.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yb.uadnd.matchcentre.data.remote.CommentaryData

@Entity(tableName = "matchInfo")
class MatchInfo(
    @PrimaryKey(autoGenerate = false)
    val matchId: Int = 0,
    val homeTeamName: String? = null,
    val homeTeamId: String? = null,
    val homeScore: Int = 0,
    val awayTeamName: String? = null,
    val awayTeamId: String? = null,
    val awayScore: Int = 0,
    val competitionId: Int = 0,
    val competition: String? = null,
    val homeTeamImageUrl: String? = null,
    val awayTeamImageUrl: String? = null,
    val lastRefreshed: Long = System.currentTimeMillis() / 1000
) {

    companion object {
        fun from(data: CommentaryData): MatchInfo {
            with(data) {
                return MatchInfo(feedMatchId, homeTeamName, homeTeamId, homeScore, awayTeamName, awayTeamId, awayScore, competitionId, competition, homeTeamImageUrl, awayTeamImageUrl)
            }
        }
    }
}