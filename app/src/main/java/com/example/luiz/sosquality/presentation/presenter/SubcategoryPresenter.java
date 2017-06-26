package com.example.luiz.sosquality.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.luiz.sosquality.domain.model.SubcategoryEntity;
import com.example.luiz.sosquality.domain.remote.ServiceGeneratorSimple;
import com.example.luiz.sosquality.domain.remote.request.FirstAidRequest;
import com.example.luiz.sosquality.presentation.contracts.ContractSubcategory;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterSubcategoryItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

public class SubcategoryPresenter implements ContractSubcategory.Presenter, CommunicatePresenterSubcategoryItem {

    private final ContractSubcategory.View mNewsView;
    private Context context;
    private boolean mFirstLoad = true;
    private String next;

    public SubcategoryPresenter(@NonNull ContractSubcategory.View mNewsView, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.mNewsView = checkNotNull(mNewsView, "newsView cannot be null!");
        this.mNewsView.setPresenter(this);
    }

    @Override
    public void llamadaSubcategorias(boolean forceUpdate, int Categoria_ID) {
        llamadaSubcategorias(forceUpdate || mFirstLoad, true, Categoria_ID);
        mFirstLoad = false;
    }

    private void llamadaSubcategorias(boolean forceUpdate, final boolean showLoadingUI, int Categoria_ID) {

        if (showLoadingUI) {
            mNewsView.setLoadingIndicator(true);
        }
        final FirstAidRequest newsSubcategoryRequest =
                ServiceGeneratorSimple.createService(FirstAidRequest.class);

        Call<ArrayList<SubcategoryEntity>> call = newsSubcategoryRequest.getSubcategories(Categoria_ID + "");

        call.enqueue(new Callback<ArrayList<SubcategoryEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<SubcategoryEntity>> call, Response<ArrayList<SubcategoryEntity>> response) {
                if (response.isSuccessful()){
                    mNewsView.showSubategories(response.body());

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
            public void onFailure(Call<ArrayList<SubcategoryEntity>> call, Throwable t) {
                if (!mNewsView.isActive()) {
                    return;
                }
                if (showLoadingUI) {
                    mNewsView.setLoadingIndicator(false);
                }
                mNewsView.showMessage("No se pudo cargar la lista de subcategorias, revise su conexión a internet");
            }
        });
    }

    @Override
    public void start() {
        //no implemented
    }

    @Override
    public void start(int id) {
        llamadaSubcategorias(false, id);
    }

    @Override
    public void clickSubcategoryItem(SubcategoryEntity subcategoryEntity) {
        mNewsView.showRecomendationsForSubcategory(subcategoryEntity);
    }
}
