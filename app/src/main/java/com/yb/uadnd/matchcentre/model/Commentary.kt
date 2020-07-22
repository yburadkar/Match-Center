package com.yb.uadnd.matchcentre.model

class Commentary {

    var data: Data? = null

    class Data {

        var id: String? = null
        var matchId: Int = 0
        var homeTeamName: String? = null
        var homeTeamId: String? = null
        var homeScore: Int = 0
        var awayTeamName: String? = null
        var awayTeamId: String? = null
        var awayScore: Int = 0
        var competitionId: Int = 0
        var competition: String? = null
        var commentaryEntries: List<CommentaryEntry>? = null

        class CommentaryEntry(
            var type: String? = null,
            var comment: String? = null,
            var time: String? = null,
            var period: String? = null
        )

    }

}
