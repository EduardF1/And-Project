package com.example.civilization_app.webservice;

import com.example.civilization_app.models.Civilization;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CivApi {

    @GET("/civ/{name}")
    Call<Civilization> getCivByName(@Path("name") String name);
}
