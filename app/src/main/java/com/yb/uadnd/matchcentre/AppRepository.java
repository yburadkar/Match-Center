package com.yb.uadnd.matchcentre;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yb.uadnd.matchcentre.model.Commentary;
import com.yb.uadnd.matchcentre.model.Commentary.Data.CommentaryEntry;
import com.yb.uadnd.matchcentre.model.Match;
import com.yb.uadnd.matchcentre.model.MatchFeedApiInterface;
import com.yb.uadnd.matchcentre.model.database.Comment;
import com.yb.uadnd.matchcentre.model.database.MovieDatabase;
import com.yb.uadnd.matchcentre.model.database.MatchInfo;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppRepository {

    private static final String BASE_URL = "https://feeds.incrowdsports.com/provider/opta/football/v1/matches/";
    private static AppRepository appRepository;
    private MatchFeedApiInterface mApi;
    private MovieDatabase mDb;
    private SimpleIdlingResource mRes;
    private MyApp mApp;
    private Executor mExecutor = Executors.newSingleThreadExecutor();

    private AppRepository(Application application) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApi = retrofit.create(MatchFeedApiInterface.class);
        mApp = (MyApp) application;
        mDb = mApp.getDatabase();
        mRes = MyApp.Companion.getIdlingResource();
    }

    public static AppRepository getInstance(Application application){
        if(appRepository == null){
            appRepository = new AppRepository(application);
        }
        return appRepository;
    }

    public void deleteOldMatchComments(@NotNull String matchId) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.getCommentDao().deleteAllMatchComments(Integer.parseInt(matchId));
            }
        });
    }

    public void fetchMatchCommentaryAndCacheInDb(String matchId){
        Call<Commentary> call = mApi.getMatchCommentary(matchId);
        if(mRes != null) mRes.setIdleState(false);
        call.enqueue(new Callback<Commentary>() {
            @Override
            public void onResponse(Call<Commentary> call, Response<Commentary> response) {
                Log.i("url: ",call.request().toString());
                Commentary comm = response.body();
                if(comm != null){
                    Commentary.Data data = comm.getData();
                    if(data != null) {
                        final MatchInfo info = new MatchInfo(data.getId(), Integer.parseInt(matchId),
                                data.getHomeTeamName(), data.getHomeTeamId(), data.getHomeScore(),
                                data.getAwayTeamName(), data.getAwayTeamId(), data.getAwayScore(),
                                data.getCompetitionId(), data.getCompetition());

                        ArrayList<CommentaryEntry> entries = data.getCommentaryEntries();
                        mExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                mDb.getMatchInfoDao().insertMatchInfo(info);
                                if(entries != null) {
                                    for (CommentaryEntry entry : entries) {
                                        mDb.getCommentDao().insertComment(
                                                new Comment(Integer.parseInt(matchId), entry));
                                    }
                                }
                            }
                        });
                    }
                }
            }
            @Override
            public void onFailure(Call<Commentary> call, Throwable t) {

            }
        });
    }

    public LiveData<Match> fetchMatch(String matchId){
        MutableLiveData<Match> matchMutableLiveData = new MutableLiveData<>();
        Call<Match> call = mApi.getMatch(matchId);
        call.enqueue(new Callback<Match>() {
            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
                Log.i("url: ",call.request().toString());
                Match match = response.body();
                if(match != null){
                    matchMutableLiveData.setValue(match);
                }
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {
                Log.i("Retrofit Call Failed", t.getMessage());
            }
        });
        return matchMutableLiveData;
    }

    @NotNull
    public LiveData<List<Comment>> getMatchComments(@NotNull String matchId) {
        return mDb.getCommentDao().getAllMatchComments(Integer.parseInt(matchId));
    }

    @NotNull
    public LiveData<MatchInfo> getMatchInfo(@NotNull String matchId) {
        return mDb.getMatchInfoDao().getMatchInfo(Integer.parseInt(matchId));
    }
}
