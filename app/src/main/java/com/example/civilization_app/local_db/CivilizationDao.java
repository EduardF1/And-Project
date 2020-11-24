package com.example.civilization_app.local_db;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.civilization_app.models.Civilization;

import java.util.List;

@Dao
public interface CivilizationDao {

    @Insert
    void insert(Civilization civilization);

    @Update
    void update(Civilization civilization);

    @Delete
    void delete(Civilization civilization);

    @Query("DELETE FROM civilization_table")
    void deleteAllCivilizations();

    @Query("SELECT * FROM civilization_table")
    LiveData<List<Civilization>> getAllCivilizations();

    @Query("SELECT * FROM civilization_table LIMIT 1")
    LiveData<Civilization> getOne();
}
