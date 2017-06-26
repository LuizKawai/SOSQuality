package com.example.luiz.sosquality.presentation.contracts;

import com.example.luiz.sosquality.domain.model.ContactEntity;
import com.example.luiz.sosquality.domain.model.SubcategoryEntity;
import com.example.luiz.sosquality.presentation.core.BasePresenter;
import com.example.luiz.sosquality.presentation.core.BaseView;

import java.util.ArrayList;

/**
 * Created by luiz on 12/13/2016.
 */

public interface ContractContactProfile {
    interface View extends BaseView<ContractContactProfile.Presenter> {
        void setLoadingIndicator(boolean active);
        void showContacts(ArrayList<ContactEntity> contactEntities);
        void showMessage(String msg);
        void showLoadingNewsError();
        void makeCall(ContactEntity contactEntity);
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void llamadaContactos(boolean forceUpdate, int userID);
    }
}
