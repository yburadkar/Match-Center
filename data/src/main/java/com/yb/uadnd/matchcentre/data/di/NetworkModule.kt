package com.yb.uadnd.matchcentre.data.di

import com.yb.uadnd.matchcentre.data.remote.CommentaryService
import com.yb.uadnd.matchcentre.data.remote.MatchService
import com.yb.uadnd.matchcentre.data.remote.MatchesDataSource
import com.yb.uadnd.matchcentre.data.remote.RemoteCommentaryDataSource
import com.yb.uadnd.matchcentre.domain.repos.CommentaryDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMatchService(retrofit: Retrofit): MatchService {
        return retrofit.create(MatchService::class.java)
    }

    @Singleton
    @Provides
    fun provideCommentaryService(retrofit: Retrofit): CommentaryService {
        return retrofit.create(CommentaryService::class.java)
    }

    @Singleton
    @Provides
    @Named("remote")
    fun provideRemoteCommentaryDataSource(remoteCommentaryDataSource: RemoteCommentaryDataSource): CommentaryDataSource = remoteCommentaryDataSource

    @Singleton
    @Provides
    fun provideMatchesDataSource(): MatchesDataSource = MatchesDataSource

    companion object {
        private const val BASE_URL = "https://feeds.incrowdsports.com/provider/opta/football/v1/matches/"
    }

}