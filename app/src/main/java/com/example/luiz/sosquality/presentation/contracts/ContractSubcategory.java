package com.example.luiz.sosquality.presentation.contracts;

import com.example.luiz.sosquality.domain.model.SubcategoryEntity;
import com.example.luiz.sosquality.presentation.core.BasePresenter;
import com.example.luiz.sosquality.presentation.core.BaseView;

import java.util.ArrayList;

public interface ContractSubcategory {
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void showSubategories(ArrayList<SubcategoryEntity> subcategoryEntity);
        void showRecomendationsForSubcategory(SubcategoryEntity subcategoryEntitiy);
        void showMessage(String msg);
        void showLoadingNewsError();
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void llamadaSubcategorias(boolean forceUpdate, int Categoria_ID);
    }
}
