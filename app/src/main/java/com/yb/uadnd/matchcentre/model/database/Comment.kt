package com.yb.uadnd.matchcentre.model.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.yb.uadnd.matchcentre.model.Commentary.Data.CommentaryEntry

@Entity(tableName = "comment")
class Comment(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var matchId: Int = 0,
    var type: String? = null,
    var comment: String? = null,
    var time: String? = null,
    var period: String? = null
) {

    @Ignore
    constructor(matchId: Int, entry: CommentaryEntry) : this(matchId = matchId, type = entry.type, comment = entry.comment, time = entry.time, period = entry.period)

    companion object {
        fun toEntries(comments: List<Comment>?): List<CommentaryEntry> {
            val entries = mutableListOf<CommentaryEntry>()
            if (comments != null) {
                for (comment in comments) {
                    entries.add(CommentaryEntry(comment.type, comment.comment, comment.time, comment.period))
                }
            }
            return entries
        }
    }
}