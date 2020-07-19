package com.yb.uadnd.matchcentre.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CommentDao {
    @Insert
    fun insertComment(comment: Comment)

    @Query("SELECT * FROM comment WHERE matchId = :matchId")
    fun getAllMatchComments(matchId: Int): LiveData<List<Comment?>?>?

    @Query("DELETE FROM comment WHERE matchId = :matchId")
    fun deleteAllMatchComments(matchId: Int)
}