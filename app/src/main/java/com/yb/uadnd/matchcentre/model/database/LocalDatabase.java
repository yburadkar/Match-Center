package com.yb.uadnd.matchcentre.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MatchInfo.class, Comment.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "MatchCentre.db";
    private static LocalDatabase instance;
    private static final Object LOCK = new Object();

    public abstract MatchInfoDao matchInfoDao();
    public abstract CommentDao commentDao();

    public static LocalDatabase getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            LocalDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
