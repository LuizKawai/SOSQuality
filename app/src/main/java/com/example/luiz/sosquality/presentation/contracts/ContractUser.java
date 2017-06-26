package com.example.luiz.sosquality.presentation.contracts;

import com.example.luiz.sosquality.domain.model.CategoryEntity;
import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.presentation.core.BasePresenter;
import com.example.luiz.sosquality.presentation.core.BaseView;

import java.util.ArrayList;

public interface ContractUser {

    interface View extends BaseView<ContractFirtsAid.Presenter> {
        void setLoadingIndicator(boolean active);
        void showUserInformation(UserEntity userEntity);
        void showContactsForUser(UserEntity userEntity);
        void showAlergicForUser(UserEntity userEntity);
        void showMessage(String msg);
        void showLoadingNewsError();
        boolean isActive();
    }

    interface Presenter extends BasePresenter{
        void actualizarUsuario(
                int dni,
                String nombre,
                float peso,
                float talla,
                String sexo,
                String tipoSangre,
                String mensajePredeterminado
        );

        void buscarUsuario(String dni);
    }
}
