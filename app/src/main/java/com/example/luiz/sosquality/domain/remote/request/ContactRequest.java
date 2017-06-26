package com.example.luiz.sosquality.domain.remote.request;

import com.example.luiz.sosquality.domain.model.ContactEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by luiz on 11/6/2016.
 */

public interface ContactRequest {
    @GET("usuario/{dni}/contacto")
    Call<ArrayList<ContactEntity>> getContacts(@Path("dni") String dni);
}
