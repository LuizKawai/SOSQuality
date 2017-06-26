package com.example.luiz.sosquality.domain.remote.request;

import com.example.luiz.sosquality.domain.model.AlergyEntity;
import com.example.luiz.sosquality.domain.model.ContactEntity;
import com.example.luiz.sosquality.domain.model.UserEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by luiz on 11/22/2016.
 */

public interface RegisterRequest {
    @POST("postusuario")
    Call<UserEntity> createUser(@Body UserEntity user);

    @POST("postcontacto")
    Call<ContactEntity> createContact(@Body ContactEntity contact);

    @POST("postalergia")
    Call<AlergyEntity> createAlergy(@Body AlergyEntity alergy);
}
