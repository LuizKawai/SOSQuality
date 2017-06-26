package com.example.luiz.sosquality.domain.remote.request;

import com.example.luiz.sosquality.domain.model.AlergyEntity;
import com.example.luiz.sosquality.domain.model.ContactEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by luiz on 12/13/2016.
 */

public interface AlergicRequest {
    @GET("usuario/{dni}/alergia")
    Call<ArrayList<AlergyEntity>> getAlergies(@Path("dni") String dni);
}
