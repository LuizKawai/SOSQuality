package com.example.luiz.sosquality.domain.remote;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luiz on 11/7/2016.
 */

public class ServiceGeneratorSimple {

    public static final String API_BASE_URL =  WS.BASE;

    private static OkHttpClient httpClient = new OkHttpClient();
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
