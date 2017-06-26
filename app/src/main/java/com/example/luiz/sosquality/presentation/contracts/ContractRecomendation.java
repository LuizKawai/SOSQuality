package com.example.luiz.sosquality.presentation.contracts;

import com.example.luiz.sosquality.domain.model.RecomendationEntity;
import com.example.luiz.sosquality.presentation.core.BasePresenter;
import com.example.luiz.sosquality.presentation.core.BaseView;

import java.util.ArrayList;

public interface ContractRecomendation {
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void showRecomendations(ArrayList<RecomendationEntity> recomendationEntity);
        void showMessage(String msg);
        void showLoadingNewsError();
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void llamadaRecomendaciones(boolean forceUpdate, int Subategoria_ID);
    }
}
