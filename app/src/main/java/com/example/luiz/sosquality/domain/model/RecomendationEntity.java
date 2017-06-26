package com.example.luiz.sosquality.domain.model;

import java.io.Serializable;

public class RecomendationEntity implements Serializable {

    private int IdRecomendacion;
    private int Parte;
    private String Descripcion;
    private int Sub_Categoria_Id;

    public RecomendationEntity() {
    }

    public RecomendationEntity(int idRecomendacion, int parte, String descripcion, int sub_Categoria_Id) {
        IdRecomendacion = idRecomendacion;
        Parte = parte;
        Descripcion = descripcion;
        Sub_Categoria_Id = sub_Categoria_Id;
    }

    public int getIdRecomendacion() {
        return IdRecomendacion;
    }

    public void setIdRecomendacion(int idRecomendacion) {
        IdRecomendacion = idRecomendacion;
    }

    public int getParte() {
        return Parte;
    }

    public void setParte(int parte) {
        Parte = parte;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getSub_Categoria_Id() {
        return Sub_Categoria_Id;
    }

    public void setSub_Categoria_Id(int sub_Categoria_Id) {
        Sub_Categoria_Id = sub_Categoria_Id;
    }
}
