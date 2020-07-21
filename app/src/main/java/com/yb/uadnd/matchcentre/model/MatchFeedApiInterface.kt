package com.yb.uadnd.matchcentre.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchFeedApiInterface {

    @GET("{matchId}/commentary")
    fun getMatchCommentary(@Path("matchId") id: String): Single<Commentary>

    @GET("{matchId}")
    fun getMatch(@Path("matchId") id: String): Single<Match>

}
