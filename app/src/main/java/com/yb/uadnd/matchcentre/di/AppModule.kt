package com.yb.uadnd.matchcentre.di

import com.yb.uadnd.matchcentre.helpers.SimpleIdlingResource
import com.yb.uadnd.matchcentre.data.repo.AppMatchCommentaryRepository
import com.yb.uadnd.matchcentre.data.repo.AppMatchRepository
import com.yb.uadnd.matchcentre.domain.repos.MatchRepository
import com.yb.uadnd.matchcentre.domain.repos.MatchCommentaryRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideMatchRepo(matchRepo: AppMatchRepository): MatchRepository = matchRepo

    @Singleton
    @Provides
    fun provideMatchCommentaryRepo(matchCommentaryRepository: AppMatchCommentaryRepository): MatchCommentaryRepository = matchCommentaryRepository

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