package com.yb.uadnd.matchcentre

import android.app.Application
import com.yb.uadnd.matchcentre.model.database.MatchCentreDatabase
import timber.log.Timber

class MyApp: Application() {

    private val matchRepo: AppRepository by lazy {
        AppRepository.getInstance(db, idlingRes)
    }
    private val db: MatchCentreDatabase by lazy {
        MatchCentreDatabase.getInstance(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    fun getMatchDb(): MatchCentreDatabase = db

    fun getMatchRepository(): AppRepository = matchRepo

    private fun initTimber() {
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    companion object{
        private var idlingRes = SimpleIdlingResource
        fun getIdlingResource(): SimpleIdlingResource = idlingRes
    }

}


