package com.yb.uadnd.matchcentre.data.di

import android.app.Application
import com.yb.uadnd.matchcentre.data.local.LocalCommentaryDataSource
import com.yb.uadnd.matchcentre.data.local.MatchCentreDatabase
import com.yb.uadnd.matchcentre.data.local.MatchInfoDao
import com.yb.uadnd.matchcentre.domain.repos.CachedCommentaryDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DbModule(private val appContext: Application) {

    @Singleton
    @Provides
    fun provideMatchDb(): MatchCentreDatabase {
        return MatchCentreDatabase.getInstance(appContext)
    }

    @Provides
    fun provideMatchInfoDao(db: MatchCentreDatabase): MatchInfoDao = db.matchInfoDao

    @Singleton
    @Provides
    @Named("localDb")
    fun provideDbCommentaryDataSource(localCommentaryDataSource: LocalCommentaryDataSource): CachedCommentaryDataSource = localCommentaryDataSource

}