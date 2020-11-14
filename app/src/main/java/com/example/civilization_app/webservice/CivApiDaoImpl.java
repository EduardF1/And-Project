package com.example.civilization_app.webservice;

import com.example.civilization_app.models.Civilization;

import retrofit2.Call;

public class CivApiDaoImpl implements CivApi {
    @Override
    public Call<Civilization> getCivByName(String name) {
        return null;
    }
}
