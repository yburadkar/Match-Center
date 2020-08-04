package com.yb.uadnd.matchcentre.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MatchInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchInfo(matchInfo: MatchInfo): Completable

    @Query("SELECT * FROM matchInfo WHERE matchId = :id")
    fun getMatchInfo(id: Int): LiveData<MatchInfo>

    @Query("SELECT lastRefreshed FROM matchInfo WHERE matchId = :matchId")
    fun getLastRefreshTime(matchId: Int): Single<List<Long>>

}