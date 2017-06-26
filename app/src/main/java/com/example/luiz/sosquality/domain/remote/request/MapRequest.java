package com.example.luiz.sosquality.domain.remote.request;

import com.example.luiz.sosquality.domain.model.HospitalEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by luiz on 11/15/2016.
 */

public interface MapRequest {
    @GET("centrosatencion")
    Call<ArrayList<HospitalEntity>> getMap();
}
