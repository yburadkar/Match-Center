package com.yb.uadnd.matchcentre

import androidx.test.espresso.IdlingResource

import java.util.concurrent.atomic.AtomicBoolean

class SimpleIdlingResource : IdlingResource {
    private val isIdle = AtomicBoolean(true)
    private var mCallback: IdlingResource.ResourceCallback? = null

    override fun getName(): String {
        return this.javaClass.simpleName
    }

    override fun isIdleNow(): Boolean {
        return isIdle.get()
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.mCallback = callback
    }

    fun setIdleState(isIdleNow: Boolean) {
        isIdle.set(isIdleNow)
        if (mCallback != null) {
            if (isIdle.get()) mCallback!!.onTransitionToIdle()
        }
    }
}
