package com.yb.uadnd.matchcentre.model

import java.util.ArrayList

class Match {
    private val status: String? = null
    val data: Data? = null
    private val metaData: MetaData? = null

    class Data {
        private val id: String? = null
        private val feedMatchId: Int = 0
        private val competition: String? = null
        private val competitionId: Int = 0
        private val status: String? = null
        private val period: String? = null
        private val seasonId: Int = 0
        private val season: String? = null
        private val round: Int = 0
        private val minute: Int = 0
        private val attendance: Int = 0
        private val date: String? = null
        val homeTeam: Team? = null
        val awayTeam: Team? = null
        private val venue: Venue? = null
        val events: ArrayList<Event>? = null
        private val officials: ArrayList<Official>? = null

        class Team {
            private val id: String? = null
            val name: String? = null
            private val shortname: String? = null
            private val score: Int = 0
            private val halfTimeScore: Int = 0
            val players: ArrayList<Player>? = null
            val teamStats: TeamStats? = null

            class Player {
                private val id: Int = 0
                private val firstName: String? = null
                private val lastName: String? = null
                val position: String? = null
                val shirtNumber: Int = 0
                private val status: String? = null
                private val captain: Boolean = false
                private val playerStats: PlayerStats? = null

                fun getPlayerName(): String {
                    return "${firstName} ${lastName}"
                }

                class PlayerStats {
                    private val bottomCentreSaves: Int = 0
                    private val bottomLeftGoals: Int = 0
                    private val bottomLeftSaves: Int = 0
                    private val bottomRightGoals: Int = 0
                    private val bottomRightSaves: Int = 0
                    private val centreBoxShots: Int = 0
                    private val concededShotsOnTargetInsideBox: Int = 0
                    private val concededShotsOnTargetOutsideBox: Int = 0
                    private val cornersLost: Int = 0
                    private val cornersTaken: Int = 0
                    private val cornersWon: Int = 0
                    private val defenderBlocks: Int = 0
                    private val directFreeKicks: Int = 0
                    private val finalThirdFouls: Int = 0
                    private val formationPlace: Int = 0
                    private val freeKickCrosses: Int = 0
                    private val goalAssists: Int = 0
                    private val goalKicks: Int = 0
                    private val goals: Int = 0
                    private val goalsConceded: Int = 0
                    private val goalsConcededInsideBox: Int = 0
                    private val handBalls: Int = 0
                    private val headerGoals: Int = 0
                    private val headerMisses: Int = 0
                    private val headerShots: Int = 0
                    private val insideBoxBlocks: Int = 0
                    private val insideBoxGoalkeeperSaves: Int = 0
                    private val insideBoxGoals: Int = 0
                    private val insideBoxMisses: Int = 0
                    private val insideBoxSaves: Int = 0
                    private val intentionalGoalAssists: Int = 0
                    private val keeperDivingSaves: Int = 0
                    private val leftBoxShots: Int = 0
                    private val leftFootShotGoals: Int = 0
                    private val leftFootShots: Int = 0
                    private val leftFootShotSaves: Int = 0
                    private val leftMisses: Int = 0
                    private val minutesPlayed: Int = 0
                    private val numberOfFouls: Int = 0
                    private val openPlayGoalAssists: Int = 0
                    private val openPlayGoals: Int = 0
                    private val openPlayShots: Int = 0
                    private val outsideBoxBlocks: Int = 0
                    private val outsideBoxCentreShots: Int = 0
                    private val outsideBoxGoalkeeperSaves: Int = 0
                    private val outsideBoxMisses: Int = 0
                    private val outsideBoxSaves: Int = 0
                    private val penaltiesConceded: Int = 0
                    private val penaltiesFaced: Int = 0
                    private val penaltyGoals: Int = 0
                    private val penaltyGoalsConceded: Int = 0
                    private val rightFootGoals: Int = 0
                    private val rightFootSaves: Int = 0
                    private val rightFootShots: Int = 0
                    private val rightMisses: Int = 0
                    private val saves: Int = 0
                    private val shotsBlocked: Int = 0
                    private val shotsOffTarget: Int = 0
                    private val shotsOnGoal: Int = 0
                    private val shotsOnTarget: Int = 0
                    private val shotsOnTargetAssists: Int = 0
                    private val started: Int = 0
                    private val subsOff: Int = 0
                    private val subsOn: Int = 0
                    private val throwIns: Int = 0
                    private val topMisses: Int = 0
                    private val topRightGoals: Int = 0
                    private val totalCornersIntoBox: Int = 0
                    private val woodworkHits: Int = 0
                    private val yellowCards: Int = 0
                }
            }

            class TeamStats {
                private val bottomCentreSaves: Int = 0
                private val bottomLeftGoals: Int = 0
                private val bottomLeftSaves: Int = 0
                private val bottomRightGoals: Int = 0
                private val bottomRightSaves: Int = 0
                private val centreBoxShots: Int = 0
                private val concededShotsOnTargetInsideBox: Int = 0
                private val concededShotsOnTargetOutsideBox: Int = 0
                private val cornersLost: Int = 0
                private val cornersTaken: Int = 0
                val cornersWon: Int = 0
                private val defenderBlocks: Int = 0
                private val defenderGoals: Int = 0
                private val directFreeKicks: Int = 0
                private val finalThirdFouls: Int = 0
                private val firstHalfGoals: Int = 0
                private val formation: Int = 0
                private val freeKickCrosses: Int = 0
                private val freeKicksConceded: Int = 0
                private val freeKicksWon: Int = 0
                private val goalAssists: Int = 0
                private val goalKicks: Int = 0
                private val goals: Int = 0
                private val goalsConceded: Int = 0
                private val goalsConcededInsideBox: Int = 0
                private val handBalls: Int = 0
                private val headerGoals: Int = 0
                private val headerMisses: Int = 0
                private val headerShots: Int = 0
                private val insideBoxBlocks: Int = 0
                private val insideBoxGoalkeeperSaves: Int = 0
                private val insideBoxGoals: Int = 0
                private val insideBoxMisses: Int = 0
                private val insideBoxSaves: Int = 0
                private val intentionalGoalAssists: Int = 0
                private val keeperDivingSaves: Int = 0
                private val leftBoxShots: Int = 0
                private val leftFootShotGoals: Int = 0
                private val leftFootShots: Int = 0
                private val leftFootShotSaves: Int = 0
                private val leftMisses: Int = 0
                private val midfielderGoals: Int = 0
                private val openPlayGoalAssists: Int = 0
                private val openPlayGoals: Int = 0
                private val openPlayShots: Int = 0
                private val outsideBoxBlocks: Int = 0
                private val outsideBoxCentreShots: Int = 0
                private val outsideBoxGoalkeeperSaves: Int = 0
                private val outsideBoxMisses: Int = 0
                private val outsideBoxSaves: Int = 0
                private val penaltiesConceded: Int = 0
                private val penaltiesFaced: Int = 0
                private val penaltiesWon: Int = 0
                private val penaltyGoals: Int = 0
                private val penaltyGoalsConceded: Int = 0
                val possession: Float = 0.0F
                private val rightFootGoals: Int = 0
                private val rightFootSaves: Int = 0
                private val rightFootShots: Int = 0
                private val rightMisses: Int = 0
                val saves: Int = 0
                private val shotsBlocked: Int = 0
                private val shotsOffTarget: Int = 0
                val shotsOnGoal: Int = 0
                val shotsOnTarget: Int = 0
                private val shotsOnTargetAssists: Int = 0
                val substitutionsMade: Int = 0
                private val teamYellowCards: Int = 0
                private val throwIns: Int = 0
                private val topMisses: Int = 0
                private val topRightGoals: Int = 0
                private val totalCornersIntoBox: Int = 0
                private val woodworkHits: Int = 0
            }
        }

        public fun getTeamStats(): ArrayList<TeamStat> {
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


        internal class Venue {
            private val id: Int = 0
            private val name: String? = null
            private val location: String? = null
        }

        class Event {
            private val eventId: Long = 0

            private val period: String? = null
            val time: String? = null
            private val optaMinute: Int = 0
            private val minute: Int = 0
            val teamId: String? = null
            val type: String? = null
            private val eventTimestamp: String? = null
            private val goalDetails: Goal? = null
            private val bookingDetails: Booking? = null
            private val substitutionDetails: Substitution? = null

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
                    goalText = goalText + " Type: ${type}"
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
                    return "${firstName} ${lastName}"
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

        internal class Official {
            private val id: Int = 0
            private val mame: String? = null
            private val type: String? = null
            private val referee: Boolean = false
        }
    }

    internal class MetaData {
        private val createdAt: String? = null
    }
}
