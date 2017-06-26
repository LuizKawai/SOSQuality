package com.example.luiz.sosquality.domain.model;

import java.io.Serializable;

/**
 * Created by luiz on 11/6/2016.
 */

public class ContactEntity implements Serializable {
    private int Usuario_DNIUsuario;
    private String NombreContacto;
    private String NumeroTelef;

    public ContactEntity() {
    }

    public ContactEntity(int usuario_DNIUsuario, String nombreContacto, String numeroTelef) {
        Usuario_DNIUsuario = usuario_DNIUsuario;
        NombreContacto = nombreContacto;
        NumeroTelef = numeroTelef;
    }

    public int getUsuario_DNIUsuario() {
        return Usuario_DNIUsuario;
    }

    public void setUsuario_DNIUsuario(int usuario_DNIUsuario) {
        Usuario_DNIUsuario = usuario_DNIUsuario;
    }

    public String getNombreContacto() {
        return NombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        NombreContacto = nombreContacto;
    }

    public String getNumeroTelef() {
        return NumeroTelef;
    }

    public void setNumeroTelef(String numeroTelef) {
        NumeroTelef = numeroTelef;
    }
}
