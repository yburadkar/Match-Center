package com.yb.uadnd.matchcentre

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable

class MyApp: Application() {

    private val mContext: Context

    init {
        mContext = applicationContext
    }

    public fun getTeamLogo(teamId: String?): Drawable? {
        return when(teamId) {
            "1" -> mContext.getDrawable(R.drawable.manunited)
            "13" -> mContext.getDrawable(R.drawable.leicestercity)
            else -> mContext.getDrawable(R.drawable.ic_launcher_foreground)
        }
    }

}