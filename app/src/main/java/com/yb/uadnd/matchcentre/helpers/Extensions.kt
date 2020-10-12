package com.yb.uadnd.matchcentre.helpers

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

fun View.setVisibility(show: Boolean, invisibleWhenHidden: Boolean = false) {
    when {
        show -> this.visibility = View.VISIBLE
        invisibleWhenHidden -> this.visibility = View.INVISIBLE
        else -> this.visibility = View.GONE
    }
}