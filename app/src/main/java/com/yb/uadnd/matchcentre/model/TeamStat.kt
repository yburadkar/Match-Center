package com.yb.uadnd.matchcentre.model

class TeamStat(var statName: String, var homeText: String, var awayText: String, var isPercent: Boolean) {

    val homeWeight: Float
    val awayWeight: Float

    init {
        if(isPercent){
            val len = homeText.length
            homeWeight = homeText.substring(0, len-1 ).toFloat()
            awayWeight = awayText.substring(0, len-1 ).toFloat()
        }else{
            homeWeight = homeText.toFloat()
            awayWeight = awayText.toFloat()
        }
    }
}