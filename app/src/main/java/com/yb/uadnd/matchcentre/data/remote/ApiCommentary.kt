package com.yb.uadnd.matchcentre.data.remote

import com.yb.uadnd.matchcentre.domain.Comment

class ApiCommentary {
    var data: ApiCommentaryData? = null
}

class ApiCommentaryData {
    val id: String? = null
    val feedMatchId: Int = 0
    val homeTeamName: String? = null
    val homeTeamId: String? = null
    val homeScore: Int = 0
    val awayTeamName: String? = null
    val awayTeamId: String? = null
    val awayScore: Int = 0
    val competitionId: Int = 0
    val competition: String? = null
    val commentaryEntries: List<ApiCommentaryEntry>? = null
    val homeTeamImageUrl: String? = null
    val awayTeamImageUrl: String? = null
}

class ApiCommentaryEntry(
    override val type: String? = null,
    override val comment: String? = null,
    override val time: String? = null,
    override val period: String? = null
) : Comment