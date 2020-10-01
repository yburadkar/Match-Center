package com.yb.uadnd.matchcentre.ui.models

import com.yb.uadnd.matchcentre.domain.MatchData

data class TeamStat(var statName: String, var homeText: String, var awayText: String, var isPercent: Boolean) {

    val homePercent: Float
    val awayPercent: Float

    init {
        if (isPercent) {
            val len = homeText.length
            homePercent = homeText.substring(0, len - 1).toFloat()
            awayPercent = awayText.substring(0, len - 1).toFloat()
        } else {
            homePercent = homeText.toFloat()
            awayPercent = awayText.toFloat()
        }
    }

    companion object {

        fun from(matchData: MatchData): List<TeamStat> {
            val stats = mutableListOf<TeamStat>()
            val homeStats = matchData.homeTeam?.teamStats
            val awayStats = matchData.awayTeam?.teamStats
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
}