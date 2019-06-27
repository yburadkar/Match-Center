package com.yb.uadnd.matchcentre.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class MainActivityViewModelFactory : ViewModelProvider.AndroidViewModelFactory {

    private var matchId: Int
    private lateinit var application: Application

    constructor(application: Application, matchId: Int) : super(application) {
        this.application = application;
        this.matchId = matchId;
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(application, matchId)
    }

}