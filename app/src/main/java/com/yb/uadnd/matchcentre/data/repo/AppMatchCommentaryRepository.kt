package com.yb.uadnd.matchcentre.data.repo

import com.yb.uadnd.matchcentre.domain.repos.CachedCommentaryDataSource
import com.yb.uadnd.matchcentre.domain.repos.MatchCommentaryRepository
import com.yb.uadnd.matchcentre.domain.models.MatchCommentary
import com.yb.uadnd.matchcentre.domain.repos.CommentaryDataSource
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

class AppMatchCommentaryRepository @Inject constructor(
    @Named("localDb") private val localCommentaryDataSource: CachedCommentaryDataSource,
    @Named("remote") private val remoteCommentaryDataSource: CommentaryDataSource
) : MatchCommentaryRepository {
    private val refreshInterval = 60

    override fun getMatchCommentary(matchId: Int): Single<MatchCommentary> {

        val dbCommentaryObservable = localCommentaryDataSource.getMatchCommentary(matchId).toObservable()
        val networkCommentaryObservable = fetchCommentaryAndSaveToCache(matchId).toObservable()

        return Observable.concat(dbCommentaryObservable, networkCommentaryObservable)
            .filter { System.currentTimeMillis()/1000 - it.lastRefreshed < refreshInterval }
            .firstOrError()
    }

    private fun fetchCommentaryAndSaveToCache(matchId: Int): Single<MatchCommentary> {
        return localCommentaryDataSource.deleteMatchCommentary(matchId)
        .flatMap { remoteCommentaryDataSource.getMatchCommentary(matchId) }
        .doOnSuccess(localCommentaryDataSource::saveMatchCommentary)
    }

}