package com.yb.uadnd.matchcentre.models

import com.yb.uadnd.matchcentre.domain.models.Booking
import com.yb.uadnd.matchcentre.domain.models.Goal
import com.yb.uadnd.matchcentre.domain.models.MatchEvent
import com.yb.uadnd.matchcentre.domain.models.Player
import com.yb.uadnd.matchcentre.domain.models.Substitution

data class UiMatchEvent(
    val time: String? = null,
    val teamId: String? = null,
    val type: String? = null,
    val goalDetails: UiGoal? = null,
    val bookingDetails: UiBooking? = null,
    val substitutionDetails: UiSubstitution? = null,
    var teamImageUrl: String? = null
) {

    fun getEventText(): String = when (type) {
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

    companion object {
        fun from(event: MatchEvent): UiMatchEvent {
            return with(event) {
                UiMatchEvent(
                    time,
                    teamId,
                    type,
                    UiGoal.from(goalDetails),
                    UiBooking.from(bookingDetails),
                    UiSubstitution.from(substitutionDetails),
                    teamImageUrl
                )
            }
        }
    }

}

data class UiGoal(
    val player: UiPlayer? = null,
    val type: String? = null
) {
    companion object {
        fun from(goal: Goal?): UiGoal? {
            return goal?.run { UiGoal(player = UiPlayer.from(player), type = type) }
        }
    }
}

data class UiPlayer(
    val firstName: String? = null,
    val lastName: String? = null
) {
    fun getPlayerName(): String = "$firstName $lastName"

    companion object {
        fun from(player: Player?): UiPlayer? {
            return player?.run { UiPlayer(firstName = firstName, lastName = lastName) }
        }
    }
}

data class UiBooking(
    val player: UiPlayer? = null,
    val type: String? = null
) {
    companion object {
        fun from(booking: Booking?): UiBooking? {
            return booking?.run { UiBooking(player = UiPlayer.from(player), type = type) }
        }
    }
}

data class UiSubstitution(
    val playerSubOff: UiPlayer? = null,
    val playerSubOn: UiPlayer? = null,
    val reason: String? = null
) {
    companion object {
        fun from(sub: Substitution?): UiSubstitution? {
            return sub?.run {
                UiSubstitution(
                    UiPlayer.from(playerSubOff),
                    UiPlayer.from(playerSubOn),
                    reason
                )
            }
        }
    }
}