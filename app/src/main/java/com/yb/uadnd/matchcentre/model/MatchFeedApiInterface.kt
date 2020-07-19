package com.yb.uadnd.matchcentre.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchFeedApiInterface {
    @GET("{matchId}/commentary")
    fun getMatchCommentary(@Path("matchId") id: String?): Call<Commentary?>?

    @GET("{matchId}")
    fun getMatch(@Path("matchId") id: String?): Call<Match?>?
}