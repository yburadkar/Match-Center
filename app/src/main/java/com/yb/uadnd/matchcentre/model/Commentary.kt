package com.yb.uadnd.matchcentre.model

import java.util.ArrayList

class Commentary {

    internal var status: String? = null
    var data: Data? = null
    internal var metadata: Metadata? = null

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
        var commentaryEntries: ArrayList<CommentaryEntry>? = null

        class CommentaryEntry {
            var type: String? = null
            var comment: String? = null
            var time: String? = null
            var period: String? = null

            constructor()

            constructor(type: String?, comment: String?, time: String?, period: String?) {
                this.type = type
                this.comment = comment
                this.time = time
                this.period = period
            }
        }
    }

    internal class Metadata {
        internal var createdAt: String? = null
    }
}
