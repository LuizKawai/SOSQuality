package com.example.luiz.sosquality.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.luiz.sosquality.domain.model.RecomendationEntity;
import com.example.luiz.sosquality.domain.remote.ServiceGeneratorSimple;
import com.example.luiz.sosquality.domain.remote.request.FirstAidRequest;
import com.example.luiz.sosquality.presentation.contracts.ContractRecomendation;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterRecomendationItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

public class RecomendationPresenter implements ContractRecomendation.Presenter, CommunicatePresenterRecomendationItem {

    private final ContractRecomendation.View mNewsView;
    private Context context;
    private boolean mFirstLoad = true;
    private String next;

    public RecomendationPresenter(@NonNull ContractRecomendation.View mNewsView, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.mNewsView = checkNotNull(mNewsView, "newsView cannot be null!");
        this.mNewsView.setPresenter(this);
    }

    @Override
    public void llamadaRecomendaciones(boolean forceUpdate, int Subcategoria_ID) {
        llamadaRecomendaciones(forceUpdate || mFirstLoad, true, Subcategoria_ID);
        mFirstLoad = false;
    }

    private void llamadaRecomendaciones(boolean forceUpdate, final boolean showLoadingUI, int Subcategoria_ID) {

        if (showLoadingUI) {
            mNewsView.setLoadingIndicator(true);
        }
        final FirstAidRequest newRecomendationRequest =
                ServiceGeneratorSimple.createService(FirstAidRequest.class);

        Call<ArrayList<RecomendationEntity>> call = newRecomendationRequest.getRecomendations(Subcategoria_ID + "");

        call.enqueue(new Callback<ArrayList<RecomendationEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<RecomendationEntity>> call, Response<ArrayList<RecomendationEntity>> response) {
                if (response.isSuccessful()){
                    mNewsView.showRecomendations(response.body());

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
            public void onFailure(Call<ArrayList<RecomendationEntity>> call, Throwable t) {
                if (!mNewsView.isActive()) {
                    return;
                }
                if (showLoadingUI) {
                    mNewsView.setLoadingIndicator(false);
                }
                mNewsView.showMessage("No se pudo cargar las recomendaciones, revise su conexión a internet");
            }
        });
    }

    @Override
    public void start() {}

    @Override
    public void start(int id) {
        llamadaRecomendaciones(false, id);
    }

    @Override
    public void clickRecomendationItem(RecomendationEntity recomendationEntity) { }
}
