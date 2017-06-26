package com.example.luiz.sosquality.domain.model;

import java.io.Serializable;

/**
 * Created by luiz on 11/6/2016.
 */

public class CategoryEntity implements Serializable {

    private int IdCategoria;
    private String Nombre;
    private String URLFoto;

    public CategoryEntity() {
    }

    public CategoryEntity(int idCategoria, String nombre, String URLFoto) {
        IdCategoria = idCategoria;
        Nombre = nombre;
        this.URLFoto = URLFoto;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        IdCategoria = idCategoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getURLFoto() {
        return URLFoto;
    }

    public void setURLFoto(String URLFoto) {
        this.URLFoto = URLFoto;
    }
}
