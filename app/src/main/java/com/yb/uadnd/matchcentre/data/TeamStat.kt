package com.yb.uadnd.matchcentre.data

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
}