package com.yb.uadnd.matchcentre.di

import android.app.Application
import com.yb.uadnd.matchcentre.AppRepository
import com.yb.uadnd.matchcentre.SimpleIdlingResource
import com.yb.uadnd.matchcentre.data.MatchService
import com.yb.uadnd.matchcentre.data.local.MatchCentreDatabase
import com.yb.uadnd.matchcentre.viewmodel.MainActivityViewModelFactory
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
class AppModule(private val appContext: Application) {

    @Provides
    fun provideMatchService(): MatchService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MatchService::class.java)
    }

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
    fun provideMatchDb(): MatchCentreDatabase {
        return MatchCentreDatabase.getInstance(appContext)
    }

    @Singleton
    @Provides
    fun provideMatchRepo(): AppRepository {
        return AppRepository(provideMatchService(), provideMatchDb(), ioScheduler(), uiScheduler(), SimpleIdlingResource)
    }

    @Singleton
    @Provides
    fun provideMainViewModelFactory(): MainActivityViewModelFactory {
        return MainActivityViewModelFactory(provideMatchRepo())
    }

    companion object {
        private const val BASE_URL = "https://feeds.incrowdsports.com/provider/opta/football/v1/matches/"
    }
}