package com.yb.uadnd.matchcentre.data.remote

import com.yb.uadnd.matchcentre.data.TeamStat
import com.yb.uadnd.matchcentre.domain.Booking
import com.yb.uadnd.matchcentre.domain.Event
import com.yb.uadnd.matchcentre.domain.Goal
import com.yb.uadnd.matchcentre.domain.Match
import com.yb.uadnd.matchcentre.domain.MatchData
import com.yb.uadnd.matchcentre.domain.Player
import com.yb.uadnd.matchcentre.domain.Substitution
import com.yb.uadnd.matchcentre.domain.Team
import com.yb.uadnd.matchcentre.domain.TeamPlayer
import com.yb.uadnd.matchcentre.domain.TeamStats

class ApiMatch(
    override val data: ApiMatchData? = null
) : Match

class ApiMatchData(
    override val id: String? = null,
    override val homeTeam: ApiTeam? = null,
    override val awayTeam: ApiTeam? = null,
    override val events: List<ApiEvent>? = null
) : MatchData {

    fun getTeamStats(): List<TeamStat> {
        val stats = mutableListOf<TeamStat>()
        val homeStats = homeTeam?.teamStats
        val awayStats = awayTeam?.teamStats
        if (homeStats != null && awayStats != null) {
            stats.add(
                TeamStat(
                    statName = "Possession",
                    homeText = "${homeStats.possession}%",
                    awayText = "${awayStats.possession}%",
                    isPercent = true
                )
            )
            stats.add(
                TeamStat(
                    statName = "Shots",
                    homeText = homeStats.shotsOnGoal.toString(),
                    awayText = awayStats.shotsOnGoal.toString(),
                    isPercent = false
                )
            )
            stats.add(
                TeamStat(
                    statName = "Shot on Target",
                    homeText = homeStats.shotsOnTarget.toString(),
                    awayText = awayStats.shotsOnTarget.toString(),
                    isPercent = false
                )
            )
            stats.add(
                TeamStat(
                    statName = "Corners",
                    homeText = homeStats.cornersWon.toString(),
                    awayText = awayStats.cornersWon.toString(),
                    isPercent = false
                )
            )
            stats.add(
                TeamStat(
                    statName = "Saves",
                    homeText = homeStats.saves.toString(),
                    awayText = awayStats.saves.toString(),
                    isPercent = false
                )
            )
            stats.add(
                TeamStat(
                    statName = "Substitutions",
                    homeText = homeStats.substitutionsMade.toString(),
                    awayText = awayStats.substitutionsMade.toString(),
                    isPercent = false
                )
            )
        }
        return stats
    }

}

class ApiTeam(
    override val id: String? = null,
    override val name: String? = null,
    override val players: List<ApiTeamPlayer>? = null,
    override val teamStats: ApiTeamStats? = null,
    override val imageUrl: String? = null
) : Team

data class ApiTeamPlayer(
    override val id: Int = 0,
    private val firstName: String? = null,
    private val lastName: String? = null,
    override val position: String? = null,
    override val shirtNumber: Int = 0
) : TeamPlayer {
    override fun getPlayerName(): String = "$firstName $lastName"
}

class ApiTeamStats(
    override val cornersWon: Int = 0,
    override val possession: Float = 0.0F,
    override val saves: Int = 0,
    override val shotsOnGoal: Int = 0,
    override val shotsOnTarget: Int = 0,
    override val substitutionsMade: Int = 0
) : TeamStats

data class ApiEvent(
    override val time: String? = null,
    override val teamId: String? = null,
    override val type: String? = null,
    private val goalDetails: ApiGoal? = null,
    private val bookingDetails: ApiBooking? = null,
    private val substitutionDetails: ApiSubstitution? = null,
    override var teamImageUrl: String? = null
) : Event {

    override fun getEventText(): String {
        return when (type) {
            "Kick Off" -> "Kick Off"
            "Half Time" -> "Half Time"
            "Second Half Start" -> "2nd Half started"
            "Full Time" -> "Full Time"
            "Goal" -> getGoalText()
            "Substitution" -> getSubstitutionText()
            "Yellow Card" -> getYellowCardText()
            "Red Card" -> getRedCardText()
            else -> "Unknown Even"
        }
    }

    override fun updateImageUrl(url: String?) {
        teamImageUrl = url
    }

    private fun getRedCardText(): String = "Red Card"

    private fun getYellowCardText(): String {
        val player = bookingDetails?.player
        return "${player?.firstName} ${player?.lastName}, ${bookingDetails?.type}"
    }

    private fun getSubstitutionText(): String {
        val playerOn = substitutionDetails?.playerSubOn
        val playerOff = substitutionDetails?.playerSubOff
        return "${playerOff?.getPlayerName()} OFF, ${playerOn?.getPlayerName()} ON. Reason: ${substitutionDetails?.reason}"
    }

    private fun getGoalText(): String {
        val player = goalDetails?.player
        var goalText = "${player?.firstName} ${player?.lastName} scores."
        val type = goalDetails?.type
        if (!type.equals("Goal"))
            goalText = "$goalText Type: $type"
        return goalText
    }

}

data class ApiGoal(
    override val player: ApiPlayer? = null,
    override val type: String? = null
) : Goal

data class ApiPlayer(
    override val firstName: String? = null,
    override val lastName: String? = null
) : Player {
    override fun getPlayerName(): String = "$firstName $lastName"
}

data class ApiBooking(
    override val player: ApiPlayer? = null,
    override val type: String? = null
) : Booking

data class ApiSubstitution(
    override val playerSubOff: ApiPlayer? = null,
    override val playerSubOn: ApiPlayer? = null,
    override val reason: String? = null
) : Substitution
