package com.example.civilization_app.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.civilization_app.models.Civilization;
import com.example.civilization_app.repository.Repository;

import java.util.List;

public class CivilizationsViewModel extends AndroidViewModel {


    private Repository repository;

    public CivilizationsViewModel(Application app) {
        super(app);
        repository = Repository.getInstance(app);
    }

    public void insertCivilization(Civilization civilization) {
        repository.insertCivilization(civilization);
    }

    public LiveData<List<Civilization>> getAllCivilizations() {
        return repository.getAllCivilizations();
    }

    public void deleteAllCivilizations(){
        repository.deleteAllCivilizations();
    }

}