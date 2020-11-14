package com.example.civilization_app.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.civilization_app.models.Civilization;
import com.example.civilization_app.repository.CivilizationRepository;

public class CivilizationsViewModel extends AndroidViewModel {


    private CivilizationRepository repository;

    public CivilizationsViewModel(Application app) {
        super(app);
        repository = CivilizationRepository.getInstance(app);
    }

    public void setCiv() {
        repository.setCiv(new Civilization("FromVM", "exp", "type"));
    }

    public LiveData<Civilization> getCiv() {
        return repository.getCiv();
    }


}