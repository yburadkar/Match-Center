package com.yb.uadnd.matchcentre;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yb.uadnd.matchcentre.model.Commentary;
import com.yb.uadnd.matchcentre.model.MatchFeedApiInterface;

import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRepository {

    private static final String BASE_URL = "https://feeds.incrowdsports.com/provider/opta/football/v1/matches/";
    private static AppRepository appRepository;
    private MatchFeedApiInterface mApi;


    private AppRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = retrofit.create(MatchFeedApiInterface.class);
    }

    public static AppRepository getInstance(){
        if(appRepository == null){
            appRepository = new AppRepository();
        }
        return appRepository;
    }

    LiveData<Commentary> fetchMatchCommentary(String matchId){
        MutableLiveData<Commentary> commentary = new MutableLiveData<>();
        Call<Commentary> call = mApi.getMatchCommentary(matchId);
        call.enqueue(new Callback<Commentary>() {
            @Override
            public void onResponse(Call<Commentary> call, Response<Commentary> response) {
                call.request().toString();
                Commentary comm = response.body();
                if(comm != null){
                    commentary.setValue(comm);
                }

            }

            @Override
            public void onFailure(Call<Commentary> call, Throwable t) {

            }
        });
        return commentary;
    }

}
