package com.yb.uadnd.matchcentre.data.local

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.yb.uadnd.matchcentre.data.remote.ApiCommentaryEntry
import com.yb.uadnd.matchcentre.domain.Comment

@Entity(tableName = "comment")
data class DbComment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val matchId: Int = 0,
    override val type: String? = null,
    override val comment: String? = null,
    override val time: String? = null,
    override val period: String? = null
) : Comment {

    @Ignore
    constructor(matchId: Int, entry: ApiCommentaryEntry) : this(matchId = matchId, type = entry.type, comment = entry.comment, time = entry.time, period = entry.period)

}