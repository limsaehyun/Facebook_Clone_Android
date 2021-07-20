package com.example.dmsproject0714;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiProvider {

    private static Retrofit instance;
    private static String BASE_URL = "http://3.143.202.235:8080/";

    public static Retrofit getInstance() {
        if(instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}
