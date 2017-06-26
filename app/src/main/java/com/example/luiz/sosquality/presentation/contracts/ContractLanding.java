package com.example.luiz.sosquality.presentation.contracts;

import android.support.annotation.NonNull;

import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.presentation.core.BasePresenter;
import com.example.luiz.sosquality.presentation.core.BaseView;

public interface ContractLanding {
    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);
        void showLoadingError(@NonNull String error);
        void userProfile(UserEntity userEntity);
        void searchUser(@NonNull String code);
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void searchUser(@NonNull String code);
    }
}
