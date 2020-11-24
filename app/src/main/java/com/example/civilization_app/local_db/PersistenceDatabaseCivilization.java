package com.example.civilization_app.local_db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.civilization_app.models.Civilization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Civilization.class}, version = 1, exportSchema = false)
public abstract class PersistenceDatabaseCivilization extends RoomDatabase {

    private static volatile PersistenceDatabaseCivilization INSTANCE;

    public abstract CivilizationDao dao();

    private static final String DATABASE_NAME = "database.db";

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static PersistenceDatabaseCivilization getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PersistenceDatabaseCivilization.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            PersistenceDatabaseCivilization.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }

            }

        }
        return INSTANCE;
    }

}
