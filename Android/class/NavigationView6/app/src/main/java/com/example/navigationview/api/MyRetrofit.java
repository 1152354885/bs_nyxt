package com.example.navigationview.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    public static Retrofit getRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        return new Retrofit.Builder()
                .baseUrl("http://192.168.139.253:8087/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
