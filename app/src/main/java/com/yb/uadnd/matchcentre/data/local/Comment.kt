package com.yb.uadnd.matchcentre.data.local

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.yb.uadnd.matchcentre.data.remote.CommentaryEntry

@Entity(tableName = "comment")
data class Comment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val matchId: Int = 0,
    val type: String? = null,
    val comment: String? = null,
    val time: String? = null,
    val period: String? = null
) {

    @Ignore
    constructor(matchId: Int, entry: CommentaryEntry) : this(matchId = matchId, type = entry.type, comment = entry.comment, time = entry.time, period = entry.period)

}