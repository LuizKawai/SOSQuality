package com.example.luiz.sosquality.domain.remote.request;

import com.example.luiz.sosquality.domain.model.UserEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserRequest {
    @GET("usuario/{dni}")
    Call<ArrayList<UserEntity>> getUser(@Path("dni") String dni);

    @POST("postusuario")
    Call<UserEntity> createUser(@Body UserEntity user);
}
