package com.example.aplikasi2bca;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    public static final String BASE_URL = "https://sandbox.bca.co.id";
    private static APIInterface api;

    Gson gson = new GsonBuilder().setLenient().create();

    public RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(APIInterface.class);
    }

    public APIInterface getService(){
        return api;
    }

}
