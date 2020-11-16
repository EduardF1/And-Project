package com.example.civilization_app.webservice;

import com.example.civilization_app.models.Civilization;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CivilizationApi {

@GET("/api/v1/civilizations")
    Call<CivilizationResponse> getCivilizations();

    @GET("/api/v1/civilization/{name}")
    Call<CivilizationResponse> getCivilizationByName(@Path("name") String name);
}

/*
    1 - civ
        @GET("/api/v1/civilization/{name}")
    Call<CivilizationResponse> getCivilizationByName(@Path("name") String name);
 */