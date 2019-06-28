package com.yb.uadnd.matchcentre.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class MainActivityViewModelFactory( matchId: Int)
    : ViewModelProvider.NewInstanceFactory() {

    private var matchId: Int
//    private lateinit var application: Application

    init {
//        this.application = application;
        this.matchId = matchId;
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(matchId) as T
    }

}