package com.example.civilization_app.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebServiceGenerator {
    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
            baseUrl("https://age-of-empires-2-api.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create());
    //https://age-of-empires-2-api.herokuapp.com
    //https://run.mocky.io/
    private static final Retrofit retrofit = retrofitBuilder.build();
    private static final CivilizationApi civilizationApi = retrofit.create(CivilizationApi.class);

    public static CivilizationApi getCivilizationApi() {
        return civilizationApi;
    }

}
