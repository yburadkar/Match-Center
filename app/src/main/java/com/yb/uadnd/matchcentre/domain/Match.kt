package com.yb.uadnd.matchcentre.domain

interface Match {
    val data: MatchData?
}

interface MatchData {
    val id: String?
    val homeTeam: MatchTeam?
    val awayTeam: MatchTeam?
    val events: List<MatchEvent>?
}

interface MatchTeam {
    val id: String?
    val name: String?
    val players: List<TeamPlayer>?
    val teamStats: TeamStats?
    val imageUrl: String?
}

interface TeamPlayer {
    val id: Int
    val firstName: String?
    val lastName: String?
    val position: String?
    val shirtNumber: Int
    fun getPlayerName(): String = "$firstName $lastName"
}

interface TeamStats {
    val cornersWon: Int
    val possession: Float
    val saves: Int
    val shotsOnGoal: Int
    val shotsOnTarget: Int
    val substitutionsMade: Int
}

interface MatchEvent {
    val time: String?
    val teamId: String?
    val type: String?
    val goalDetails: Goal?
    val bookingDetails: Booking?
    val substitutionDetails: Substitution?
    var teamImageUrl: String?
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
        if (!type.equals("Goal"))
            goalText = "$goalText Type: $type"
        return goalText
    }

}

interface Goal {
    val player: Player?
    val type: String?
}

interface Player {
    val firstName: String?
    val lastName: String?
    fun getPlayerName(): String = "$firstName $lastName"
}

interface Booking {
    val player: Player?
    val type: String?
}

interface Substitution {
    val playerSubOff: Player?
    val playerSubOn: Player?
    val reason: String?
}
