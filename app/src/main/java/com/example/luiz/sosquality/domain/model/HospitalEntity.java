package com.example.luiz.sosquality.domain.model;

import java.io.Serializable;

/**
 * Created by luiz on 11/6/2016.
 */

public class HospitalEntity implements Serializable {

    private float Latitud;
    private float Longitud;
    private String NombreCentAten;
    private String Direccion;
    private int Telefono;
    private String URLFoto;
    private String Distrito;

    public HospitalEntity() {
    }

    public HospitalEntity(float latitud, float longitud, String nombreCentAten, String direccion, int telefono, String URLFoto, String distrito) {
        Latitud = latitud;
        Longitud = longitud;
        NombreCentAten = nombreCentAten;
        Direccion = direccion;
        Telefono = telefono;
        this.URLFoto = URLFoto;
        Distrito = distrito;
    }

    public float getLatitud() {
        return Latitud;
    }

    public void setLatitud(float latitud) {
        Latitud = latitud;
    }

    public float getLongitud() {
        return Longitud;
    }

    public void setLongitud(float longitud) {
        Longitud = longitud;
    }

    public String getNombreCentAten() {
        return NombreCentAten;
    }

    public void setNombreCentAten(String nombreCentAten) {
        NombreCentAten = nombreCentAten;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int telefono) {
        Telefono = telefono;
    }

    public String getURLFoto() {
        return URLFoto;
    }

    public void setURLFoto(String URLFoto) {
        this.URLFoto = URLFoto;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String distrito) {
        Distrito = distrito;
    }
}
