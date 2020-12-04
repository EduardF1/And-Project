package com.example.civilization_app.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.civilization_app.local_db.CivilizationDao;
import com.example.civilization_app.local_db.PersistenceDatabaseCivilization;
import com.example.civilization_app.models.Civilization;
import com.example.civilization_app.webservice.CivilizationApi;
import com.example.civilization_app.webservice.CivilizationResponse;
import com.example.civilization_app.webservice.WebServiceGenerator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private final CivilizationDao civilizationDao;
    private static Repository instance;
    private final LiveData<List<Civilization>> allCivilizations;
    private final MutableLiveData<Civilization> civilization;
    private final MutableLiveData<ArrayList<Civilization>> civilizationsFromWeb;

    private Repository(Application application) {
        civilizationDao = PersistenceDatabaseCivilization.getInstance(application).dao();
        allCivilizations = civilizationDao.getAllCivilizations();
        civilization = new MutableLiveData<>();
        civilizationsFromWeb = new MutableLiveData<>();

    }

    public static synchronized Repository getInstance(Application application) {
        if (instance == null) {
            instance = new Repository(application);
        }
        return instance;
    }

    public void insertCivilization(Civilization civilization) {
        PersistenceDatabaseCivilization.databaseWriteExecutor.execute(() -> {
            civilizationDao.insert(civilization);
        });
    }

    public LiveData<List<Civilization>> getAllCivilizations() {
        PersistenceDatabaseCivilization.databaseWriteExecutor.execute(() -> {
            civilizationDao.getAllCivilizations();
        });
        return allCivilizations;
    }



    public void deleteAllCivilizations() {
        PersistenceDatabaseCivilization.databaseWriteExecutor.execute(() -> {
            civilizationDao.deleteAllCivilizations();
        });
    }


    // WEB
    public LiveData<Civilization> getCivilizationFromWeb() {
        return civilization;
    }

    public LiveData<ArrayList<Civilization>> getAllCivilizationsFromWeb() {
        CivilizationApi civilizationApi = WebServiceGenerator.getCivilizationApi();
        Call<CivilizationResponse> call = civilizationApi.getCivilizations();
        call.enqueue(new Callback<CivilizationResponse>() {
            @Override
            public void onResponse(@NotNull Call<CivilizationResponse> call, Response<CivilizationResponse> response) {
                if (response.code() == 200) {
                    civilizationsFromWeb.setValue(response.body().getCivilizations());
                }
            }

            @Override
            public void onFailure(Call<CivilizationResponse> call, Throwable t) {
                Log.i("Retrofit", "Error on request attempt");
            }
        });
        return civilizationsFromWeb;
    }

    public void updateCivilization(String civilizationName) {
        CivilizationApi civilizationApi = WebServiceGenerator.getCivilizationApi();
        Call<CivilizationResponse> call = civilizationApi.getCivilizationByName(civilizationName);
        call.enqueue(new Callback<CivilizationResponse>() {
            @Override
            public void onResponse(@NotNull Call<CivilizationResponse> call, Response<CivilizationResponse> response) {
                if (response.code() == 200) {
                    civilization.setValue(response.body().getCivilization());
                    Civilization civilization_new = new Civilization();
                    civilization_new.setCivilizationName(response.body().getCivilization().getCivilizationName());
                    civilization_new.setArmyType(response.body().getCivilization().getArmyType());
                    civilization_new.setExpansion(response.body().getCivilization().getExpansion());
                    insertCivilization(civilization_new);
                }
            }

            @Override
            public void onFailure(Call<CivilizationResponse> call, Throwable t) {
                Log.i("Retrofit", "Error on request attempt");
            }
        });
    }
}
