package com.example.luiz.sosquality.presentation.contracts;

import com.example.luiz.sosquality.domain.model.AlergyEntity;
import com.example.luiz.sosquality.domain.model.ContactEntity;
import com.example.luiz.sosquality.presentation.core.BasePresenter;
import com.example.luiz.sosquality.presentation.core.BaseView;

import java.util.ArrayList;

/**
 * Created by luiz on 12/13/2016.
 */

public interface ContractAlergicProfile {

    interface View extends BaseView<ContractAlergicProfile.Presenter> {
        void setLoadingIndicator(boolean active);
        void showAlergics(ArrayList<AlergyEntity> alergyEntitiesEntities);
        void showMessage(String msg);
        void showLoadingNewsError();
        void addNewAlergic(AlergyEntity alergyEntity);
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void llamadaAlergias(boolean forceUpdate, int userID);
        void addAlergic();
    }
}
