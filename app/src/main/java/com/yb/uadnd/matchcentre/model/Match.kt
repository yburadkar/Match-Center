package com.yb.uadnd.matchcentre.model

import java.util.ArrayList

class Match {
    val status: String? = null
    val data: Data? = null
    val metaData: MetaData? = null

    class Data {
        val id: String? = null
        val feedMatchId: Int = 0
        val competition: String? = null
        val competitionId: Int = 0
        val status: String? = null
        val period: String? = null
        val seasonId: Int = 0
        val season: String? = null
        val round: Int = 0
        val minute: Int = 0
        val attendance: Int = 0
        val date: String? = null
        val homeTeam: Team? = null
        val awayTeam: Team? = null
        val venue: Venue? = null
        val events: ArrayList<Event>? = null
        val officials: ArrayList<Official>? = null

        class Team {
            val id: String? = null
            val name: String? = null
            val shortname: String? = null
            val score: Int = 0
            val halfTimeScore: Int = 0
            val players: ArrayList<Player>? = null
            val teamStats: TeamStats? = null

            class Player {
                val id: Int = 0
                val firstName: String? = null
                val lastName: String? = null
                val position: String? = null
                val shirtNumber: Int = 0
                val status: String? = null
                val captain: Boolean = false
                val playerStats: PlayerStats? = null

                fun getPlayerName(): String {
                    return "$firstName $lastName"
                }

                class PlayerStats {
                    val bottomCentreSaves: Int = 0
                    val bottomLeftGoals: Int = 0
                    val bottomLeftSaves: Int = 0
                    val bottomRightGoals: Int = 0
                    val bottomRightSaves: Int = 0
                    val centreBoxShots: Int = 0
                    val concededShotsOnTargetInsideBox: Int = 0
                    val concededShotsOnTargetOutsideBox: Int = 0
                    val cornersLost: Int = 0
                    val cornersTaken: Int = 0
                    val cornersWon: Int = 0
                    val defenderBlocks: Int = 0
                    val directFreeKicks: Int = 0
                    val finalThirdFouls: Int = 0
                    val formationPlace: Int = 0
                    val freeKickCrosses: Int = 0
                    val goalAssists: Int = 0
                    val goalKicks: Int = 0
                    val goals: Int = 0
                    val goalsConceded: Int = 0
                    val goalsConcededInsideBox: Int = 0
                    val handBalls: Int = 0
                    val headerGoals: Int = 0
                    val headerMisses: Int = 0
                    val headerShots: Int = 0
                    val insideBoxBlocks: Int = 0
                    val insideBoxGoalkeeperSaves: Int = 0
                    val insideBoxGoals: Int = 0
                    val insideBoxMisses: Int = 0
                    val insideBoxSaves: Int = 0
                    val intentionalGoalAssists: Int = 0
                    val keeperDivingSaves: Int = 0
                    val leftBoxShots: Int = 0
                    val leftFootShotGoals: Int = 0
                    val leftFootShots: Int = 0
                    val leftFootShotSaves: Int = 0
                    val leftMisses: Int = 0
                    val minutesPlayed: Int = 0
                    val numberOfFouls: Int = 0
                    val openPlayGoalAssists: Int = 0
                    val openPlayGoals: Int = 0
                    val openPlayShots: Int = 0
                    val outsideBoxBlocks: Int = 0
                    val outsideBoxCentreShots: Int = 0
                    val outsideBoxGoalkeeperSaves: Int = 0
                    val outsideBoxMisses: Int = 0
                    val outsideBoxSaves: Int = 0
                    val penaltiesConceded: Int = 0
                    val penaltiesFaced: Int = 0
                    val penaltyGoals: Int = 0
                    val penaltyGoalsConceded: Int = 0
                    val rightFootGoals: Int = 0
                    val rightFootSaves: Int = 0
                    val rightFootShots: Int = 0
                    val rightMisses: Int = 0
                    val saves: Int = 0
                    val shotsBlocked: Int = 0
                    val shotsOffTarget: Int = 0
                    val shotsOnGoal: Int = 0
                    val shotsOnTarget: Int = 0
                    val shotsOnTargetAssists: Int = 0
                    val started: Int = 0
                    val subsOff: Int = 0
                    val subsOn: Int = 0
                    val throwIns: Int = 0
                    val topMisses: Int = 0
                    val topRightGoals: Int = 0
                    val totalCornersIntoBox: Int = 0
                    val woodworkHits: Int = 0
                    val yellowCards: Int = 0
                }
            }

            class TeamStats {
                val bottomCentreSaves: Int = 0
                val bottomLeftGoals: Int = 0
                val bottomLeftSaves: Int = 0
                val bottomRightGoals: Int = 0
                val bottomRightSaves: Int = 0
                val centreBoxShots: Int = 0
                val concededShotsOnTargetInsideBox: Int = 0
                val concededShotsOnTargetOutsideBox: Int = 0
                val cornersLost: Int = 0
                val cornersTaken: Int = 0
                val cornersWon: Int = 0
                val defenderBlocks: Int = 0
                val defenderGoals: Int = 0
                val directFreeKicks: Int = 0
                val finalThirdFouls: Int = 0
                val firstHalfGoals: Int = 0
                val formation: Int = 0
                val freeKickCrosses: Int = 0
                val freeKicksConceded: Int = 0
                val freeKicksWon: Int = 0
                val goalAssists: Int = 0
                val goalKicks: Int = 0
                val goals: Int = 0
                val goalsConceded: Int = 0
                val goalsConcededInsideBox: Int = 0
                val handBalls: Int = 0
                val headerGoals: Int = 0
                val headerMisses: Int = 0
                val headerShots: Int = 0
                val insideBoxBlocks: Int = 0
                val insideBoxGoalkeeperSaves: Int = 0
                val insideBoxGoals: Int = 0
                val insideBoxMisses: Int = 0
                val insideBoxSaves: Int = 0
                val intentionalGoalAssists: Int = 0
                val keeperDivingSaves: Int = 0
                val leftBoxShots: Int = 0
                val leftFootShotGoals: Int = 0
                val leftFootShots: Int = 0
                val leftFootShotSaves: Int = 0
                val leftMisses: Int = 0
                val midfielderGoals: Int = 0
                val openPlayGoalAssists: Int = 0
                val openPlayGoals: Int = 0
                val openPlayShots: Int = 0
                val outsideBoxBlocks: Int = 0
                val outsideBoxCentreShots: Int = 0
                val outsideBoxGoalkeeperSaves: Int = 0
                val outsideBoxMisses: Int = 0
                val outsideBoxSaves: Int = 0
                val penaltiesConceded: Int = 0
                val penaltiesFaced: Int = 0
                val penaltiesWon: Int = 0
                val penaltyGoals: Int = 0
                val penaltyGoalsConceded: Int = 0
                val possession: Float = 0.0F
                val rightFootGoals: Int = 0
                val rightFootSaves: Int = 0
                val rightFootShots: Int = 0
                val rightMisses: Int = 0
                val saves: Int = 0
                val shotsBlocked: Int = 0
                val shotsOffTarget: Int = 0
                val shotsOnGoal: Int = 0
                val shotsOnTarget: Int = 0
                val shotsOnTargetAssists: Int = 0
                val substitutionsMade: Int = 0
                val teamYellowCards: Int = 0
                val throwIns: Int = 0
                val topMisses: Int = 0
                val topRightGoals: Int = 0
                val totalCornersIntoBox: Int = 0
                val woodworkHits: Int = 0
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


        class Venue {
            val id: Int = 0
            val name: String? = null
            val location: String? = null
        }

        class Event {
            val eventId: Long = 0
            val period: String? = null
            val time: String? = null
            val optaMinute: Int = 0
            val minute: Int = 0
            val teamId: String? = null
            val type: String? = null
            val eventTimestamp: String? = null
            val goalDetails: Goal? = null
            val bookingDetails: Booking? = null
            val substitutionDetails: Substitution? = null

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

            private fun getRedCardText(): String {
                return "Red Card"
            }

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

            class Goal {
                val player: Player? = null
                val type: String? = null
            }

            class Player {
                val playerId: Int = 0
                val firstName: String? = null
                val lastName: String? = null
                val known: String? = null

                fun getPlayerName(): String{
                    return "$firstName $lastName"
                }
            }

            class Booking {
                val player: Player? = null
                val type: String? = null
            }

            class Substitution {
                val playerSubOff: Player? = null
                val playerSubOn: Player? = null
                val reason: String? = null
            }
        }

        class Official {
            private val id: Int = 0
            private val mame: String? = null
            private val type: String? = null
            private val referee: Boolean = false
        }
    }

    class MetaData {
        private val createdAt: String? = null
    }
}
