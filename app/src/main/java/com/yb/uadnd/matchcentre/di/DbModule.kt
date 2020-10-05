package com.yb.uadnd.matchcentre.di

import android.app.Application
import com.yb.uadnd.matchcentre.data.local.MatchCentreDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule(private val appContext: Application) {

    @Singleton
    @Provides
    fun provideMatchDb(): MatchCentreDatabase {
        return MatchCentreDatabase.getInstance(appContext)
    }

}