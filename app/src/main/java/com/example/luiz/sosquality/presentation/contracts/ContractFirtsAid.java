package com.example.luiz.sosquality.presentation.contracts;

import com.example.luiz.sosquality.domain.model.CategoryEntity;
import com.example.luiz.sosquality.presentation.core.BasePresenter;
import com.example.luiz.sosquality.presentation.core.BaseView;

import java.util.ArrayList;

public interface ContractFirtsAid {
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void showCategories(ArrayList<CategoryEntity> categoryEntity);
        void showSubcategoryForCategory(CategoryEntity categoryEntitiy);
        void showMessage(String msg);
        void showLoadingNewsError();
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        void llamadaCategorias(boolean forceUpdate);
    }
}
