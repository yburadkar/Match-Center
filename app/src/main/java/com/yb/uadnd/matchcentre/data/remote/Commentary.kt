package com.yb.uadnd.matchcentre.data.remote

class Commentary {
    var data: CommentaryData? = null
}

class CommentaryData {
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
    val commentaryEntries: List<CommentaryEntry>? = null
    val homeTeamImageUrl: String? = null
    val awayTeamImageUrl: String? = null
}

class CommentaryEntry(
    val type: String? = null,
    val comment: String? = null,
    val time: String? = null,
    val period: String? = null
)