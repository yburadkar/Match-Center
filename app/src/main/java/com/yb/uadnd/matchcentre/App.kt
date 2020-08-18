package com.yb.uadnd.matchcentre

import android.app.Application
import com.yb.uadnd.matchcentre.di.AppComponent
import com.yb.uadnd.matchcentre.di.AppModule
import com.yb.uadnd.matchcentre.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDi()
        initTimber()
    }

    private fun initDi() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

}


