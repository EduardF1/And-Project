package com.example.civilization_app.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.civilization_app.local_db.CivilizationDao;
import com.example.civilization_app.local_db.PersistenceDatabaseCivilization;
import com.example.civilization_app.models.Civilization;
import com.example.civilization_app.webservice.CivilizationApi;
import com.example.civilization_app.webservice.CivilizationFromWeb;
import com.example.civilization_app.webservice.CivilizationResponse;
import com.example.civilization_app.webservice.WebServiceGenerator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private CivilizationDao civilizationDao;
    private CivilizationApi civilizationApiDao;
    private static Repository instance;
    private LiveData<List<Civilization>> allCivilizations;
    private MutableLiveData<CivilizationFromWeb>civilization;
    private MutableLiveData<ArrayList<Civilization>> civilizationsFromWeb;

    private Repository(Application application){
//        CivilizationDatabase database = CivilizationDatabase.getInstance(application);
        civilizationDao = PersistenceDatabaseCivilization.getInstance(application).dao();
       allCivilizations = civilizationDao.getAllCivilizations();
       // civilization = civilizationDao.getOne();
        //civilizationApiDao = new CivilizationApiDaoImpl();
        civilization = new MutableLiveData<>();
        civilizationsFromWeb = new MutableLiveData<>();

    }

    public static synchronized Repository getInstance(Application application){
        if(instance == null)
        {
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
    public LiveData<CivilizationFromWeb> getCivilizationFromWeb()
    {
        return civilization;
    }

    public LiveData<ArrayList<Civilization>> getAllCivilizationsFromWeb(){return civilizationsFromWeb;}

    public void updateCivilization(String civilizationName) {
        CivilizationApi civilizationApi = WebServiceGenerator.getCivilizationApi();
        Call<CivilizationResponse> call = civilizationApi.getCivilizationByName(civilizationName);
        call.enqueue(new Callback<CivilizationResponse>() {
            @Override
            public void onResponse(@NotNull Call<CivilizationResponse> call, Response<CivilizationResponse> response) {
                if (response.code() == 200) {
                    civilization.setValue(response.body().getCivilization());
                }
            }

            @Override
            public void onFailure(Call<CivilizationResponse> call, Throwable t) {
                Log.i("Retrofit", "Error on request attempt");
            }
        });
    }
}


    /*
    public LiveData<List<Civilization>> getAllCivilizations(){
        return allCivilizations;
    }
*/
    /*
    public void insert(Civilization civilization){
        new InsertCivilizationAsync(civilizationDao).execute(civilization);
    }
*/

/*


    private static class InsertCivilizationAsync extends AsyncTask<Civilization,Void,Void> {
        private CivilizationDao civilizationDao;

        private InsertCivilizationAsync(CivilizationDao civilizationDao) {
            this.civilizationDao = civilizationDao;
        }

        @Override
        protected Void doInBackground(Civilization... civilizations) {
            civilizationDao.insert(civilizations[0]);
            return null;
        }
    }
*/
/*
    private LiveData<Civilization> civilization;
    public LiveData<Civilization> getCiv() {

        return civilization;
    }
*/