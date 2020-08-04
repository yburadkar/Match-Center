package com.yb.uadnd.matchcentre

import timber.log.Timber

object Utils {

    fun getEventTypeStyle(type: String?): TypeStyle {
        return when(type) {
            "Kick Off" -> TypeStyle("START 1", R.color.grey)
            "Half Time" -> TypeStyle("END 1", R.color.grey)
            "Second Half Start" -> TypeStyle("START 2", R.color.grey)
            "Full Time" -> TypeStyle("END 2", R.color.grey)
            "Goal" -> TypeStyle("GOAL", R.color.green)
            "Substitution" -> TypeStyle("SUB", R.color.purple)
            "Yellow Card" -> TypeStyle("YELLOW", R.color.yellow)
            "Red Card" -> TypeStyle("RED", R.color.red)
            else -> {
                Timber.e("Unknown type: $type")
                TypeStyle("", R.color.red)
            }
        }
    }

    fun getCommentaryTypeStyle(type: String?): TypeStyle {
        return when(type) {
            "end 14" -> TypeStyle("FINISH", R.color.grey)
            "end 2" -> TypeStyle("END 2", R.color.grey)
            "end 1" -> TypeStyle("END 1", R.color.grey)
            "start2" -> TypeStyle("START 2", R.color.grey)
            "start1" -> TypeStyle("START 1", R.color.grey)
            "end delay" -> TypeStyle("RESUME", R.color.grey)
            "start delay" -> TypeStyle("PAUSED", R.color.grey)
            "miss" -> TypeStyle("MISS", R.color.lime)
            "post" -> TypeStyle("POST", R.color.lime)
            "corner" -> TypeStyle("CORNER", R.color.orange)
            "goal" -> TypeStyle("GOAL", R.color.green)
            "attempt blocked" -> TypeStyle("BLOCK", R.color.blue)
            "attempt saved" -> TypeStyle("SAVE", R.color.indigo)
            "offside" -> TypeStyle("OFFSIDE", R.color.black)
            "substitution" -> TypeStyle("SUB", R.color.purple)
            "free kick lost" -> TypeStyle("FREEKICK", R.color.pink)
            "free kick won" -> TypeStyle("FREEKICK", R.color.brown)
            "yellow card" -> TypeStyle("YELLOW", R.color.yellow)
            "red card" -> TypeStyle("RED", R.color.red)
            "lineup" -> TypeStyle("LINEUP", R.color.dark_green)
            "player retired" -> TypeStyle("RET", R.color.red)
            else -> {
                Timber.i( "Unknown type: $type")
                TypeStyle("", R.color.red)
            }
        }
    }

    class TypeStyle(var text: String, var colorRes: Int)
}