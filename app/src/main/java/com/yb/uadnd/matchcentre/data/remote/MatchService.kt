package com.yb.uadnd.matchcentre.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchService {

    @GET("{matchId}")
    fun getMatch(@Path("matchId") id: String): Single<Match>

}
