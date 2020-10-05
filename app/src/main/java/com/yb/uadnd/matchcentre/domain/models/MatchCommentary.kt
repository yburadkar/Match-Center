package com.yb.uadnd.matchcentre.domain.models

import com.yb.uadnd.matchcentre.domain.Comment

interface MatchCommentary {
    val feedMatchId: Int
    val homeTeamName: String?
    val homeScore: Int
    val awayTeamName: String?
    val awayScore: Int
    val competition: String?
    val commentaryEntries: List<Comment>?
    val homeTeamImageUrl: String?
    val awayTeamImageUrl: String?
    val lastRefreshed: Long
}