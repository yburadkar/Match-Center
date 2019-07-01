package com.yb.uadnd.matchcentre.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MatchInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMatchInfo(MatchInfo matchInfo);

    @Query("SELECT * FROM matchInfo WHERE matchId = :matchId")
    LiveData<MatchInfo> getMatchInfo(int matchId);

    @Delete
    void deleteMatch(MatchInfo matchInfo);
}
