package com.yb.uadnd.matchcentre.data.local.models

import androidx.room.Embedded
import androidx.room.Relation

class DbMatchInfoComments(
    @Embedded
    val matchInfo: DbMatchInfo,
    @Relation(parentColumn = "matchId", entityColumn = "matchId")
    val comments: List<DbComment>
)