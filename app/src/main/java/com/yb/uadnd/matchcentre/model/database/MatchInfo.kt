package com.yb.uadnd.matchcentre.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yb.uadnd.matchcentre.model.Commentary

@Entity(tableName = "matchInfo")
class MatchInfo (
    var id: String? = null,
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
    var awayTeamImageUrl: String? = null
) {

    companion object {
        fun from(data: Commentary.Data): MatchInfo {
            with(data) {
                return MatchInfo(null, matchId, homeTeamName, homeTeamId, homeScore, awayTeamName, awayTeamId, awayScore, competitionId, competition, homeTeamImageUrl, awayTeamImageUrl)
            }
        }
    }
}