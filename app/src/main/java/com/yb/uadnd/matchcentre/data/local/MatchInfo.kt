package com.yb.uadnd.matchcentre.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yb.uadnd.matchcentre.data.CommentaryData

@Entity(tableName = "matchInfo")
class MatchInfo (
    @PrimaryKey(autoGenerate = false)
    var matchId: Int = 0,
    var homeTeamName: String? = null,
    var homeTeamId: String? = null,
    var homeScore: Int = 0,
    var awayTeamName: String? = null,
    var awayTeamId: String? = null,
    var awayScore: Int = 0,
    var competitionId: Int = 0,
    var competition: String? = null,
    var homeTeamImageUrl: String? = null,
    var awayTeamImageUrl: String? = null,
    var lastRefreshed: Long = System.currentTimeMillis()/1000
) {

    companion object {
        fun from(data: CommentaryData): MatchInfo {
            with(data) {
                return MatchInfo( feedMatchId, homeTeamName, homeTeamId, homeScore, awayTeamName, awayTeamId, awayScore, competitionId, competition, homeTeamImageUrl, awayTeamImageUrl)
            }
        }
    }
}