package com.yb.uadnd.matchcentre

import android.app.Application
import com.yb.uadnd.matchcentre.model.MatchService
import com.yb.uadnd.matchcentre.model.database.MatchCentreDatabase
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class MyApp: Application() {

    val db: MatchCentreDatabase by lazy {
        MatchCentreDatabase.getInstance(applicationContext)
    }

    private val matchService: MatchService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MatchService::class.java)
    }

    val matchRepo: AppRepository by lazy {
        AppRepository.getInstance(matchService, db, idlingRes)
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    companion object{

        private const val BASE_URL = "https://feeds.incrowdsports.com/provider/opta/football/v1/matches/"
        private var idlingRes = SimpleIdlingResource

        fun getIdlingResource(): SimpleIdlingResource = idlingRes

    }

}


