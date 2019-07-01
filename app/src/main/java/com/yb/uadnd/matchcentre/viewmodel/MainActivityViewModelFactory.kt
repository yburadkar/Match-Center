package com.yb.uadnd.matchcentre.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class MainActivityViewModelFactory(private var application: Application, private var matchId: Int)
    : ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainActivityViewModel(application, matchId) as T
    }

}