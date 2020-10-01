package com.yb.uadnd.matchcentre.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yb.uadnd.matchcentre.data.local.models.DbCommentaryMatchInfo
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MatchInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchInfo(matchInfo: DbCommentaryMatchInfo): Completable

    @Query("SELECT * FROM matchInfo WHERE matchId = :id")
    fun getMatchInfo(id: Int): LiveData<DbCommentaryMatchInfo>

    @Query("SELECT lastRefreshed FROM matchInfo WHERE matchId = :matchId")
    fun getLastRefreshTime(matchId: Int): Single<List<Long>>

}