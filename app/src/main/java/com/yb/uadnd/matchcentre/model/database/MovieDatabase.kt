package com.yb.uadnd.matchcentre.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MatchInfo::class, Comment::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract val matchInfoDao: MatchInfoDao
    abstract val commentDao: CommentDao

    companion object {
        private const val DATABASE_NAME = "MatchCentre.db"
        @Volatile
        private var INSTANCE: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            MovieDatabase::class.java,
                            DATABASE_NAME
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}