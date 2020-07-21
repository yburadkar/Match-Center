package com.yb.uadnd.matchcentre

import androidx.lifecycle.LiveData
import com.yb.uadnd.matchcentre.model.Commentary
import com.yb.uadnd.matchcentre.model.Match
import com.yb.uadnd.matchcentre.model.MatchFeedApiInterface
import com.yb.uadnd.matchcentre.model.database.Comment
import com.yb.uadnd.matchcentre.model.database.MovieDatabase
import com.yb.uadnd.matchcentre.model.database.MatchInfo
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AppRepository(private val mDb: MovieDatabase,
                    private val mRes: SimpleIdlingResource) {

    private val BASE_URL = "https://feeds.incrowdsports.com/provider/opta/football/v1/matches/"
    private var mApiService: MatchFeedApiInterface

    init {
        val retrofit =  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        mApiService = retrofit.create(MatchFeedApiInterface::class.java)
    }

    fun fetchMatchCommentaryAndCacheInDb(matchId: String): Single<Commentary> {
        mRes.setIdleState(false)
        return mApiService.getMatchCommentary(matchId)
    }

    fun fetchMatch(matchId: String): Single<Match> {
        return mApiService.getMatch(matchId)
    }

    fun deleteOldMatchComments(matchId: String): Completable {
        return mDb.commentDao.deleteAllMatchComments(matchId.toInt())
    }

    fun getMatchComments(matchId: String): LiveData<List<Comment>> {
        return mDb.commentDao.getAllMatchComments(matchId.toInt())
    }

    fun getMatchInfo(matchId: String): LiveData<MatchInfo>{
        return mDb.matchInfoDao.getMatchInfo(matchId.toInt())
    }

    companion object {
        private var instance: AppRepository? = null

        fun getInstance(roomDatabase: MovieDatabase, res: SimpleIdlingResource): AppRepository {
            if(instance == null) {
                instance = AppRepository(roomDatabase, res)
            }
            return instance as AppRepository
        }
    }
}