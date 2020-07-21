package com.yb.uadnd.matchcentre

import android.app.Application
import com.yb.uadnd.matchcentre.model.database.MovieDatabase
import timber.log.Timber

class MyApp: Application() {

    private lateinit var matchRepo: AppRepository
    private lateinit var db: MovieDatabase

    override fun onCreate() {
        super.onCreate()
        db = MovieDatabase.getInstance(applicationContext)
        matchRepo = AppRepository.getInstance(db, idlingRes)
        initTimber()
    }

    fun getRepository(): AppRepository = matchRepo

    private fun initTimber() {
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    fun getDatabase(): MovieDatabase = db

    companion object{
        private var idlingRes = SimpleIdlingResource()
        fun getIdlingResource(): SimpleIdlingResource = idlingRes
    }

}