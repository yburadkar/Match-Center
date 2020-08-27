package com.yb.uadnd.matchcentre.domain

interface CommentaryMatchInfo {
    val matchId: Int
    val homeTeamName: String?
    val homeTeamId: String?
    val homeScore: Int
    val awayTeamName: String?
    val awayTeamId: String?
    val awayScore: Int
    val competitionId: Int
    val competition: String?
    val homeTeamImageUrl: String?
    val awayTeamImageUrl: String?
    val lastRefreshed: Long
}