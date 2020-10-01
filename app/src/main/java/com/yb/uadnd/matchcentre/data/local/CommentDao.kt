package com.yb.uadnd.matchcentre.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yb.uadnd.matchcentre.data.local.models.DbComment

@Dao
interface CommentDao {

    @Insert
    fun insertComment(comment: DbComment)

    @Query("SELECT * FROM comment WHERE matchId = :matchId")
    fun getAllMatchComments(matchId: Int): LiveData<List<DbComment>>

    @Query("DELETE FROM comment WHERE matchId = :matchId")
    fun deleteAllMatchComments(matchId: Int)
}