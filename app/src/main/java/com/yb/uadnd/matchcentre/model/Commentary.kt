package com.yb.uadnd.matchcentre.model

import java.util.ArrayList

class Commentary {

    internal var status: String? = null
    var data: Data? = null
        internal set
    internal var metadata: Metadata? = null

    class Data {

        internal var id: String? = null
        internal var matchId: Int = 0
        internal var homeTeamName: String? = null
        internal var homeTeamId: String? = null
        var homeScore: Int = 0
        internal var awayTeamName: String? = null
        internal var awayTeamId: String? = null
        var awayScore: Int = 0
        internal var competitionId: Int = 0
        internal var competition: String? = null
        var commentaryEntries: ArrayList<CommentaryEntry>? = null

        class CommentaryEntry {
            var type: String? = null
            var comment: String? = null
            var time: String? = null
            var period: String? = null
        }
    }

    internal class Metadata {
        internal var createdAt: String? = null
    }
}
