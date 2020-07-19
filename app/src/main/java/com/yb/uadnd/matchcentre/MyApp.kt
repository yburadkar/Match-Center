package com.yb.uadnd.matchcentre

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import com.yb.uadnd.matchcentre.model.database.MovieDatabase

class MyApp: Application() {

    private lateinit var mContext: Context
    private lateinit var mRepository: AppRepository
    private lateinit var mDb: MovieDatabase

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        mDb = MovieDatabase.getInstance(mContext)
        mRepository = AppRepository.getInstance(this)
    }

    public fun getDatabase(): MovieDatabase {
        return mDb
    }

    fun getRepository(): AppRepository{
        return mRepository
    }

    public fun getTeamLogo(teamId: String?): Drawable? {
        return when(teamId) {
            "1" -> mContext.getDrawable(R.drawable.manunited)
            "13" -> mContext.getDrawable(R.drawable.leicestercity)
            else -> mContext.getDrawable(R.drawable.ic_launcher_foreground)
        }
    }
    companion object{
        private var mIdlingRes = SimpleIdlingResource()
        public fun getIdlingResource(): SimpleIdlingResource{
            return mIdlingRes
        }
    }

}