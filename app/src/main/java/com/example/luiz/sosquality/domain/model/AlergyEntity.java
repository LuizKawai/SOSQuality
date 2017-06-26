package com.example.luiz.sosquality.domain.model;

import java.io.Serializable;

/**
 * Created by luiz on 11/6/2016.
 */

public class AlergyEntity implements Serializable {

    private int Usuario_DNIUsuario;
    private String NombreAlergia;
    private String Medicacion;

    public AlergyEntity() {
    }

    public AlergyEntity(int usuario_DNIUsuario, String nombreAlergia, String medicacion) {
        Usuario_DNIUsuario = usuario_DNIUsuario;
        NombreAlergia = nombreAlergia;
        Medicacion = medicacion;
    }

    public int getUsuario_DNIUsuario() {
        return Usuario_DNIUsuario;
    }

    public void setUsuario_DNIUsuario(int usuario_DNIUsuario) {
        Usuario_DNIUsuario = usuario_DNIUsuario;
    }

    public String getNombreAlergia() {
        return NombreAlergia;
    }

    public void setNombreAlergia(String nombreAlergia) {
        NombreAlergia = nombreAlergia;
    }

    public String getMedicacion() {
        return Medicacion;
    }

    public void setMedicacion(String medicacion) {
        Medicacion = medicacion;
    }
}
