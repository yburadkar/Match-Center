package com.yb.uadnd.matchcentre.domain.models

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

    fun updateImageUrl(url: String?) {
        teamImageUrl = url
    }

}

interface Goal {
    val player: Player?
    val type: String?
}

interface Player {
    val firstName: String?
    val lastName: String?
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
