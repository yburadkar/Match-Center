package com.yb.uadnd.matchcentre

import android.app.Application
import android.content.Context
import com.yb.uadnd.matchcentre.model.database.MovieDatabase
import timber.log.Timber

class MyApp: Application() {

    private lateinit var mContext: Context
    private lateinit var mRepository: AppRepository
    private lateinit var mDb: MovieDatabase

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        mDb = MovieDatabase.getInstance(mContext)
        mRepository = AppRepository.getInstance(mDb, mIdlingRes)
        initTimber()
    }

    fun getRepository(): AppRepository{
        return mRepository
    }

    private fun initTimber() {
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    fun getDatabase(): MovieDatabase {
        return mDb
    }

    companion object{
        private var mIdlingRes = SimpleIdlingResource()
        fun getIdlingResource(): SimpleIdlingResource = mIdlingRes
    }

}