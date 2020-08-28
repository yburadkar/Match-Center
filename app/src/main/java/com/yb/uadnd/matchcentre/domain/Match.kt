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
    val position: String?
    val shirtNumber: Int
    fun getPlayerName(): String
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
    var teamImageUrl: String?
    fun getEventText(): String
    fun updateImageUrl(url: String?)
}

interface Goal {
    val player: Player?
    val type: String?
}

interface Player {
    val firstName: String?
    val lastName: String?
    fun getPlayerName(): String
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
