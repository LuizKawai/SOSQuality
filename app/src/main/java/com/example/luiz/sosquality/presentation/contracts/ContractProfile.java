package com.example.luiz.sosquality.presentation.contracts;

import android.support.annotation.NonNull;

import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.presentation.core.BasePresenter;
import com.example.luiz.sosquality.presentation.core.BaseView;

public interface ContractProfile {

    interface View extends BaseView<ContractProfile.Presenter> {
        void setLoadingIndicator(boolean active);
        void showUserInformation(UserEntity userEntity);
        void showContactsForUser(UserEntity userEntity);
        void showAlergicForUser(UserEntity userEntity);
        void showMaps();
        void showMessage(String msg);
        void showLoadingNewsError();
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void loadProfile(boolean forceUpdate, int userDNI);
        void loadUserInformation(UserEntity userEntity);
        void loadUserContactInformation(String userID);
        void loadUserAlergicInformation(String userID);
    }
}
