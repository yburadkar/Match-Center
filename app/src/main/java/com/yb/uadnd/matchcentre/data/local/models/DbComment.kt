package com.yb.uadnd.matchcentre.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.yb.uadnd.matchcentre.data.remote.models.ApiCommentaryEntry
import com.yb.uadnd.matchcentre.domain.Comment

@Entity(
    tableName = "comment",
    foreignKeys = [ForeignKey(
        entity = DbMatchInfo::class,
        parentColumns = ["matchId"],
        childColumns = ["matchId"],
        onDelete = CASCADE,
        deferred = true
    )]
)
class DbComment(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(index = true)
    val matchId: Int = 0,
    override val type: String? = null,
    override val comment: String? = null,
    override val time: String? = null,
    override val period: String? = null
) : Comment {

    @Ignore
    constructor(matchId: Int, entry: ApiCommentaryEntry) : this(matchId = matchId, type = entry.type, comment = entry.comment, time = entry.time, period = entry.period)

}