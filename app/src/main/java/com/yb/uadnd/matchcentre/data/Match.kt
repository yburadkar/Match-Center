package com.yb.uadnd.matchcentre.data

import java.util.ArrayList

class Match {
    val data: Data? = null

    class Data {
        val id: String? = null
        val homeTeam: Team? = null
        val awayTeam: Team? = null
        val events: ArrayList<Event>? = null

        class Team {
            val id: String? = null
            val name: String? = null
            val players: ArrayList<Player>? = null
            val teamStats: TeamStats? = null
            val imageUrl: String? = null

            data class Player (
                val id: Int = 0,
                private val firstName: String? = null,
                private val lastName: String? = null,
                val position: String? = null,
                val shirtNumber: Int = 0
            ){

                fun getPlayerName(): String = "$firstName $lastName"

            }

            class TeamStats {
                val cornersWon: Int = 0
                val possession: Float = 0.0F
                val saves: Int = 0
                val shotsOnGoal: Int = 0
                val shotsOnTarget: Int = 0
                val substitutionsMade: Int = 0
            }
        }

        fun getTeamStats(): ArrayList<TeamStat> {
            val stats = ArrayList<TeamStat>()
            val homeStats = homeTeam?.teamStats
            val awayStats = awayTeam?.teamStats
            if(homeStats != null && awayStats != null) {
                stats.add(TeamStat("Possession", "${homeStats.possession}%",
                    "${awayStats.possession}%", true))
                stats.add(TeamStat("Shots", homeStats.shotsOnGoal.toString(),
                    awayStats.shotsOnGoal.toString(),false))
                stats.add(TeamStat("Shot on Target", homeStats.shotsOnTarget.toString(),
                    awayStats.shotsOnTarget.toString(), false))
                stats.add(TeamStat("Corners", homeStats.cornersWon.toString(),
                    awayStats.cornersWon.toString(), false))
                stats.add(TeamStat("Saves", homeStats.saves.toString(),
                    awayStats.saves.toString(), false))
                stats.add(TeamStat("Substitutions", homeStats.substitutionsMade.toString(),
                    awayStats.substitutionsMade.toString(), false))
            }
            return stats
        }

        data class Event (
            val time: String? = null,
            val teamId: String? = null,
            val type: String? = null,
            private val goalDetails: Goal? = null,
            private val bookingDetails: Booking? = null,
            private val substitutionDetails: Substitution? = null,
            var teamImageUrl: String? = null
        ) {

            fun getEventText(): String {
                return when(type) {
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

            fun updateImageUrl(url: String?) {
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
                if(!type.equals("Goal"))
                    goalText = "$goalText Type: $type"
                return goalText
            }

            data class Goal (
                val player: Player? = null,
                val type: String? = null
            )

            data class Player (
                val firstName: String? = null,
                val lastName: String? = null
            ) {
                fun getPlayerName(): String = "$firstName $lastName"
            }

            data class Booking (
                val player: Player? = null,
                val type: String? = null
            )

            data class Substitution (
                val playerSubOff: Player? = null,
                val playerSubOn: Player? = null,
                val reason: String? = null
            )
        }

    }

}
