package com.yb.uadnd.matchcentre.data.local

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.yb.uadnd.matchcentre.data.CommentaryEntry

@Entity(tableName = "comment")
data class Comment(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var matchId: Int = 0,
    var type: String? = null,
    var comment: String? = null,
    var time: String? = null,
    var period: String? = null
) {

    @Ignore
    constructor(matchId: Int, entry: CommentaryEntry) : this(matchId = matchId, type = entry.type, comment = entry.comment, time = entry.time, period = entry.period)

}