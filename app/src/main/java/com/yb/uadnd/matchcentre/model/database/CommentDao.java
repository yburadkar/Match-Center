package com.yb.uadnd.matchcentre.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CommentDao {

    @Insert
    void insertComment(Comment comment);

    @Query("SELECT * FROM comment WHERE matchId = :matchId")
    LiveData<List<Comment>> getAllMatchComments(int matchId);

}
