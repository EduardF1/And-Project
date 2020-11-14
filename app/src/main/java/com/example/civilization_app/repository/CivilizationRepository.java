package com.example.civilization_app.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.civilization_app.local_db.CivilizationDao;
import com.example.civilization_app.local_db.CivilizationDatabase;
import com.example.civilization_app.local_db.PersistenceDatabaseCivilization;
import com.example.civilization_app.models.Civilization;
import com.example.civilization_app.webservice.CivApi;
import com.example.civilization_app.webservice.CivApiDaoImpl;

import java.util.List;

public class CivilizationRepository {

    private CivilizationDao civilizationDao;
    private CivApi civApiDao;
    private static CivilizationRepository instance;
    private LiveData<List<Civilization>> allCivilizations;

    private CivilizationRepository(Application application){
//        CivilizationDatabase database = CivilizationDatabase.getInstance(application);
        civilizationDao = PersistenceDatabaseCivilization.getInstance(application).dao();
//        allCivilizations = civilizationDao.getAllCivilizations();
        civilization = civilizationDao.getOne();
        civApiDao = new CivApiDaoImpl();



    }



    public static synchronized CivilizationRepository getInstance(Application application){
        if(instance == null)
        {
            instance = new CivilizationRepository(application);
        }
        return instance;
    }

    public LiveData<List<Civilization>> getAllCivilizations(){
        return allCivilizations;
    }

    public void insert(Civilization civilization){
        new InsertCivilizationAsync(civilizationDao).execute(civilization);
    }



    //TODO: ADD THE REST OF THE REPOSITORY METHODS

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


    private LiveData<Civilization> civilization;
    public LiveData<Civilization> getCiv() {

        return civilization;
    }


    public void setCiv(Civilization civ) {
        PersistenceDatabaseCivilization.databaseWriteExecutor.execute(() -> {
            civilizationDao.insert(civ);
        });

    }


//TODO: ADD THE REST OF THE ASYNC TASK EXECUTION HELPER CLASSES

}
