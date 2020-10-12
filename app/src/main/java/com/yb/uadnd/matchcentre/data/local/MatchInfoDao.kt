package com.yb.uadnd.matchcentre.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.yb.uadnd.matchcentre.data.local.models.DbComment
import com.yb.uadnd.matchcentre.data.local.models.DbMatchInfo
import com.yb.uadnd.matchcentre.data.local.models.DbMatchInfoComments
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface MatchInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchInfo(matchInfo: DbMatchInfo): Completable

    @Query("SELECT * FROM matchInfo WHERE matchId = :id")
    fun getMatchInfo(id: Int): LiveData<DbMatchInfo>

    @Query("SELECT lastRefreshed FROM matchInfo WHERE matchId = :matchId")
    fun getLastRefreshTime(matchId: Int): Single<List<Long>>

    @Query("DELETE FROM matchInfo WHERE matchId = :matchId")
    fun deleteMatchInfo(matchId: Int): Maybe<Int>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatchInfoWithComments(matchInfo: DbMatchInfo, comments: List<DbComment>)

    @Transaction
    @Query("SELECT * FROM matchInfo WHERE matchId = :matchId")
    fun getMatchInfoWithComments(matchId: Int): Maybe<DbMatchInfoComments>

}