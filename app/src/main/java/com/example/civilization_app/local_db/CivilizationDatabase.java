package com.example.civilization_app.local_db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.civilization_app.models.Civilization;

@Database(entities = {Civilization.class}, version = 1, exportSchema = false)
public abstract class CivilizationDatabase extends RoomDatabase {

    private static CivilizationDatabase instance;
    public abstract CivilizationDao getCivilizationDao();

    public static synchronized CivilizationDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CivilizationDatabase.class, "civilization_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
