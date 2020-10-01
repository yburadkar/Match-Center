package com.yb.uadnd.matchcentre.ui.models

import com.yb.uadnd.matchcentre.domain.TeamPlayer

data class UiTeamPlayer(
    val id: Int = 0,
    val firstName: String? = null,
    val lastName: String? = null,
    val position: String? = null,
    val shirtNumber: Int = 0
) {
    fun getPlayerName(): String = "$firstName $lastName"

    companion object {
        fun from(player: TeamPlayer): UiTeamPlayer {
            return player.run { UiTeamPlayer(id, firstName, lastName, position, shirtNumber) }
        }
    }
}