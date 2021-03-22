package com.example.dynamoxquiz.services;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private static final String BASE_URL = "https://quiz-api-bwi5hjqyaq-uc.a.run.app";

    private static RetrofitFactory instance;
    private ApiService service;

    private RetrofitFactory () {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build();

        service = retrofit.create(ApiService.class);
    }

    public static RetrofitFactory getInstance() {
        if (instance == null) {
            instance = new RetrofitFactory();
        }

        return instance;
    }

    public ApiService getService() {
        return service;
    }
}
