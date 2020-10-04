package com.yb.uadnd.matchcentre.domain

interface CommentaryMatchInfo {
    val matchId: Int
    val homeTeamName: String?
    val homeScore: Int
    val awayTeamName: String?
    val awayScore: Int
    val competition: String?
    val homeTeamImageUrl: String?
    val awayTeamImageUrl: String?
    val lastRefreshed: Long
}