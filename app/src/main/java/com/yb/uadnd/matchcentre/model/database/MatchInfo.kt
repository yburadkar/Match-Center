package com.yb.uadnd.matchcentre.model.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

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
    var competition: String? = null
)