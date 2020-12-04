package com.example.civilization_app.webservice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CivilizationApi {

    ///api/v1/civilizations
    ///v3/55801590-399d-4811-8586-aea3ff8c7f3b/civilizations
    @GET("api/v1/civilizations")
    Call<CivilizationResponse> getCivilizations();

    ///api/v1/civilization/{name}
    ///v3/55801590-399d-4811-8586-aea3ff8c7f3b/civilizations/{name}
    @GET("api/v1/civilization/{name}")
    Call<CivilizationResponse> getCivilizationByName(@Path("name") String name);
}


