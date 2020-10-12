package com.yb.uadnd.matchcentre.di

import com.yb.uadnd.matchcentre.SimpleIdlingResource
import com.yb.uadnd.matchcentre.data.remote.CommentaryService
import com.yb.uadnd.matchcentre.data.remote.MatchService
import com.yb.uadnd.matchcentre.data.remote.MatchesDataSource
import com.yb.uadnd.matchcentre.data.repo.AppCommentaryRepository
import com.yb.uadnd.matchcentre.data.repo.AppMatchRepository
import com.yb.uadnd.matchcentre.domain.MatchRepository
import com.yb.uadnd.matchcentre.domain.repos.CommentaryRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideMatchRepo(matchRepo: AppMatchRepository): MatchRepository = matchRepo

    @Singleton
    @Provides
    fun provideCommentaryRepo(commRepo: AppCommentaryRepository): CommentaryRepository = commRepo

    @Singleton
    @Provides
    @Named("io")
    fun ioScheduler(): Scheduler = Schedulers.io()

    @Singleton
    @Provides
    @Named("ui")
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Singleton
    @Provides
    fun provideIdlingRes(): SimpleIdlingResource = SimpleIdlingResource

}