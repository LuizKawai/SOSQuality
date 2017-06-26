package com.example.luiz.sosquality.domain.model;

import java.io.Serializable;

/**
 * Created by luiz on 11/6/2016.
 */

public class UserEntity implements Serializable{

    private int DNIUsuario;
    private String NombreUsuario;
    private Float Peso;
    private Float Talla;
    private String Sexo;
    private String TipoSangre;
    private String MensajePrederteminado;

    public UserEntity() {
    }

    public UserEntity(int DNIUsuario, String nombreUsuario, Float peso, Float talla, String sexo, String tipoSangre, String mensajePrederteminado) {
        this.DNIUsuario = DNIUsuario;
        NombreUsuario = nombreUsuario;
        Peso = peso;
        Talla = talla;
        Sexo = sexo;
        TipoSangre = tipoSangre;
        MensajePrederteminado = mensajePrederteminado;
    }

    public int getDNIUsuario() {
        return DNIUsuario;
    }

    public void setDNIUsuario(int DNIUsuario) {
        this.DNIUsuario = DNIUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        NombreUsuario = nombreUsuario;
    }

    public Float getPeso() {
        return Peso;
    }

    public void setPeso(Float peso) {
        Peso = peso;
    }

    public Float getTalla() {
        return Talla;
    }

    public void setTalla(Float talla) {
        Talla = talla;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getTipoSangre() {
        return TipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        TipoSangre = tipoSangre;
    }

    public String getMensajePrederteminado() {
        return MensajePrederteminado;
    }

    public void setMensajePrederteminado(String mensajePrederteminado) {
        MensajePrederteminado = mensajePrederteminado;
    }
}
