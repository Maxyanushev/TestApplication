package com.example.testapplication.data;

import static com.example.testapplication.utils.Constants.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {
    private static NetworkModule instance;
    private final Retrofit retrofit;
    private NetworkModule(){
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkModule getInstance(){
        if (instance == null){
            instance = new NetworkModule();
        }
        return instance;
    }

    public Api getService(){
        return retrofit.create(Api.class);
    }
}
