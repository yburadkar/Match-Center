package com.yb.uadnd.matchcentre.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MatchInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchInfo(matchInfo: MatchInfo?)

    @Query("SELECT * FROM matchInfo WHERE matchId = :matchId")
    fun getMatchInfo(matchId: Int): LiveData<MatchInfo?>?

    @Delete
    fun deleteMatch(matchInfo: MatchInfo?)
}