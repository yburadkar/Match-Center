package com.yb.uadnd.matchcentre.helpers

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class DisposingViewModel : ViewModel() {

    val disposables = CompositeDisposable()

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}