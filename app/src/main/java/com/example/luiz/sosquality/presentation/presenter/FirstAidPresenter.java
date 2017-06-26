package com.example.luiz.sosquality.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.luiz.sosquality.domain.model.CategoryEntity;
import com.example.luiz.sosquality.domain.remote.ServiceGeneratorSimple;
import com.example.luiz.sosquality.domain.remote.request.FirstAidRequest;
import com.example.luiz.sosquality.presentation.contracts.ContractFirtsAid;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterCategoryItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

public class FirstAidPresenter implements ContractFirtsAid.Presenter, CommunicatePresenterCategoryItem {

    private final ContractFirtsAid.View mNewsView;
    private Context context;
    private boolean mFirstLoad = true;

    public FirstAidPresenter(@NonNull ContractFirtsAid.View mNewsView, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.mNewsView = checkNotNull(mNewsView, "newsView cannot be null!");
        this.mNewsView.setPresenter(this);
    }

    @Override
    public void llamadaCategorias(boolean forceUpdate) {
            llamadaCategorias(forceUpdate || mFirstLoad, true);
            mFirstLoad = false;
    }

    public void llamadaCategorias(boolean forceUpdate, final boolean showLoadingUI) {
        if (showLoadingUI) {
            mNewsView.setLoadingIndicator(true);
        }
        final FirstAidRequest newsCategoryRequest =
                ServiceGeneratorSimple.createService(FirstAidRequest.class);

        Call<ArrayList<CategoryEntity>> call = newsCategoryRequest.getCategories();

        call.enqueue(new Callback<ArrayList<CategoryEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<CategoryEntity>> call, Response<ArrayList<CategoryEntity>> response) {
                if (response.isSuccessful()){
                    mNewsView.showCategories(response.body());

                    if (!mNewsView.isActive()) {
                        return;
                    }
                    if (showLoadingUI) {
                        mNewsView.setLoadingIndicator(false);
                    }

                } else {
                    if (!mNewsView.isActive()) {
                        return;
                    }
                    if (showLoadingUI) {
                        mNewsView.setLoadingIndicator(false);
                    }
                    mNewsView.showLoadingNewsError();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CategoryEntity>> call, Throwable t) {
                if (!mNewsView.isActive()) {
                    return;
                }
                if (showLoadingUI) {
                    mNewsView.setLoadingIndicator(false);
                }
                mNewsView.showMessage("No se pudo cargar la lista de categorias, revise su conexión a internet");
            }
        });
    }

    @Override
    public void start() {
        llamadaCategorias(false);
    }

    @Override
    public void start(int id) {
        // no implemented
    }

    @Override
    public void clickCategoryItem(CategoryEntity categoryEntity) {
        mNewsView.showSubcategoryForCategory(categoryEntity);
    }
}
