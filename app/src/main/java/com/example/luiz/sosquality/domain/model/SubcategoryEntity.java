package com.example.luiz.sosquality.domain.model;

import java.io.Serializable;

/**
 * Created by luiz on 11/6/2016.
 */

public class SubcategoryEntity implements Serializable {

    private int IdSubcategoria;
    private int Categoria_Id;
    private String Nombre;
    private String URLFoto;

    public SubcategoryEntity() {
    }

    public SubcategoryEntity(int idSubcategoria, int categoria_Id, String nombre, String URLFoto) {
        IdSubcategoria = idSubcategoria;
        Categoria_Id = categoria_Id;
        Nombre = nombre;
        this.URLFoto = URLFoto;
    }

    public int getIdSubcategoria() {
        return IdSubcategoria;
    }

    public void setIdSubcategoria(int idSubcategoria) {
        IdSubcategoria = idSubcategoria;
    }

    public int getCategoria_Id() {
        return Categoria_Id;
    }

    public void setCategoria_Id(int categoria_Id) {
        Categoria_Id = categoria_Id;
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
