package com.yb.uadnd.matchcentre.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MatchFeedApiInterface {

    @GET("{matchId}/commentary")
    Call<Commentary> getMatchCommentary(@Path("matchId") String matchId);

}
