package com.yb.uadnd.matchcentre.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentaryService {

    @GET("{matchId}/commentary")
    fun getMatchCommentary(@Path("matchId") id: String): Single<Commentary>

}