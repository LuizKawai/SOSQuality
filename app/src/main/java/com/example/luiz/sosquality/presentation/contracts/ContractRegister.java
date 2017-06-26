package com.example.luiz.sosquality.presentation.contracts;

import com.example.luiz.sosquality.presentation.core.BasePresenter;
import com.example.luiz.sosquality.presentation.core.BaseView;

/**
 * Created by luiz on 11/10/2016.
 */

public interface ContractRegister {

    interface Presenter extends BasePresenter {
        void registroContacto(int Usuario_DNIUsuario, String NombreContacto, String NumeroTelef);
        void registroAlergia(int Usuario_DNIUsuario, String NombreAlergia, String Medicacion);
        void regitroUsuario(int dni, String nombre, float peso, float talla, String sexo,
                            String tipoSangre, String mensajePredeterminado);
    }

    interface View extends BaseView<Presenter>{
        void setErrorDNI();
        void setErrorName();
        void navigateToHome();
    }
}
