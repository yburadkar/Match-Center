package com.yb.uadnd.matchcentre

import androidx.lifecycle.LiveData
import com.yb.uadnd.matchcentre.model.Commentary
import com.yb.uadnd.matchcentre.model.Match
import com.yb.uadnd.matchcentre.model.MatchService
import com.yb.uadnd.matchcentre.model.database.Comment
import com.yb.uadnd.matchcentre.model.database.MatchCentreDatabase
import com.yb.uadnd.matchcentre.model.database.MatchInfo
import io.reactivex.Completable
import io.reactivex.Single

class AppRepository(
    private var matchService: MatchService,
    private val db: MatchCentreDatabase,
    private val idlingResource: SimpleIdlingResource
) {

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

        fun getInstance(service: MatchService, roomDatabase: MatchCentreDatabase, res: SimpleIdlingResource): AppRepository {
            if(instance == null) {
                instance = AppRepository(service, roomDatabase, res)
            }
            return instance as AppRepository
        }
    }
}