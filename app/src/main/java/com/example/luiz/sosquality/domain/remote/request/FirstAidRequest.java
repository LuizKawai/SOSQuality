package com.example.luiz.sosquality.domain.remote.request;

import com.example.luiz.sosquality.domain.model.CategoryEntity;
import com.example.luiz.sosquality.domain.model.RecomendationEntity;
import com.example.luiz.sosquality.domain.model.SubcategoryEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FirstAidRequest {

    @GET("categorias")
    Call<ArrayList<CategoryEntity>> getCategories();

    @GET("categorias/{id}/subcategorias")
    Call<ArrayList<SubcategoryEntity>> getSubcategories(@Path("id") String id);

    @GET("categorias/subcategoria/{id}/recomendacion")
    Call<ArrayList<RecomendationEntity>> getRecomendations(@Path("id") String id);

}
