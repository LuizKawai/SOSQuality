package com.example.luiz.sosquality.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.luiz.sosquality.domain.model.AlergyEntity;
import com.example.luiz.sosquality.domain.remote.ServiceGeneratorSimple;
import com.example.luiz.sosquality.domain.remote.request.AlergicRequest;
import com.example.luiz.sosquality.presentation.contracts.ContractAlergicProfile;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterAlergicProfileItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by luiz on 12/13/2016.
 */

public class AlergicProfilePresenter implements ContractAlergicProfile.Presenter, CommunicatePresenterAlergicProfileItem{

    private final ContractAlergicProfile.View mNewsView;
    private Context context;
    private boolean mFirstLoad = true;
    private String next;

    public AlergicProfilePresenter(@NonNull ContractAlergicProfile.View mNewsView, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.mNewsView = checkNotNull(mNewsView, "newsView cannot be null!");
        this.mNewsView.setPresenter(this);
    }

    @Override
    public void llamadaAlergias(boolean forceUpdate, int userID) {
        llamadaAlergias(forceUpdate || mFirstLoad, true, userID);
        mFirstLoad = false;
    }

    @Override
    public void addAlergic() {

    }

    private void llamadaAlergias(boolean forceUpdate, final boolean showLoadingUI, int userID) {

        if (showLoadingUI) {
            mNewsView.setLoadingIndicator(true);
        }
        final AlergicRequest newAlergicRequest =
                ServiceGeneratorSimple.createService(AlergicRequest.class);

        Call<ArrayList<AlergyEntity>> call = newAlergicRequest.getAlergies(String.valueOf(userID));

        call.enqueue(new Callback<ArrayList<AlergyEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<AlergyEntity>> call, Response<ArrayList<AlergyEntity>> response) {
                if (response.isSuccessful()){
                    mNewsView.showAlergics(response.body());

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
            public void onFailure(Call<ArrayList<AlergyEntity>> call, Throwable t) {
                if (!mNewsView.isActive()) {
                    return;
                }
                if (showLoadingUI) {
                    mNewsView.setLoadingIndicator(false);
                }
                mNewsView.showMessage("No se pudo cargar la lista de alergias, revise su conexión a internet");
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void start(int id) {
        llamadaAlergias(false, id);
    }

    @Override
    public void clickContactItem(AlergyEntity alergyEntity) {

    }
}
