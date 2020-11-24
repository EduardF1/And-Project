package com.example.civilization_app.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.civilization_app.models.Civilization;
import com.example.civilization_app.repository.Repository;


import java.util.ArrayList;

public class SearchViewModel extends AndroidViewModel {


    Repository repository;

    public SearchViewModel(Application app) {
        super(app);
        repository = Repository.getInstance(app);
    }



    public LiveData<Civilization> getCivilizationFromWeb(){
        return repository.getCivilizationFromWeb();
    }


    public void updateCivilization(String param){
        repository.updateCivilization(param);
    }

    public LiveData<ArrayList<Civilization>> getAllCivilizationsFromWeb(){
        return repository.getAllCivilizationsFromWeb();
    }

    public void addToOwned(Civilization civilization) {
        repository.insertCivilization(civilization);
    }
}