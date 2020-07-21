package com.yb.uadnd.matchcentre

import androidx.lifecycle.LiveData
import com.yb.uadnd.matchcentre.model.Commentary
import com.yb.uadnd.matchcentre.model.Match
import com.yb.uadnd.matchcentre.model.MatchService
import com.yb.uadnd.matchcentre.model.database.Comment
import com.yb.uadnd.matchcentre.model.database.MovieDatabase
import com.yb.uadnd.matchcentre.model.database.MatchInfo
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AppRepository(private val db: MovieDatabase,
                    private val idlingResource: SimpleIdlingResource) {

    private val BASE_URL = "https://feeds.incrowdsports.com/provider/opta/football/v1/matches/"
    private var matchService: MatchService

    init {
        val retrofit =  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        matchService = retrofit.create(MatchService::class.java)
    }

    fun fetchMatchCommentaryAndCacheInDb(matchId: String): Single<Commentary> {
        idlingResource.setIdleState(false)
        return matchService.getMatchCommentary(matchId)
    }

    fun fetchMatch(matchId: String): Single<Match> {
        return matchService.getMatch(matchId)
    }

    fun deleteOldMatchComments(matchId: String): Completable {
        return db.commentDao.deleteAllMatchComments(matchId.toInt())
    }

    fun getMatchComments(matchId: String): LiveData<List<Comment>> {
        return db.commentDao.getAllMatchComments(matchId.toInt())
    }

    fun getMatchInfo(matchId: String): LiveData<MatchInfo>{
        return db.matchInfoDao.getMatchInfo(matchId.toInt())
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