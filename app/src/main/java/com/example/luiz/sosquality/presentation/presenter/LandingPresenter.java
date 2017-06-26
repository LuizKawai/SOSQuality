package com.example.luiz.sosquality.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.domain.remote.ServiceGeneratorSimple;
import com.example.luiz.sosquality.domain.remote.request.UserRequest;
import com.example.luiz.sosquality.presentation.contracts.ContractLanding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

public class LandingPresenter implements ContractLanding.Presenter {

    private final ContractLanding.View mLandingView;
    private Context context;
    private boolean mFirstLoad = true;
    private String next;


    public LandingPresenter(@NonNull ContractLanding.View mLandingView, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.mLandingView = checkNotNull(mLandingView, "newsView cannot be null!");
        this.mLandingView.setPresenter(this);
    }

    @Override
    public void searchUser(@NonNull String dni) {
        UserRequest userRequest =
                ServiceGeneratorSimple.createService(UserRequest.class);
        Call<ArrayList<UserEntity>> call = userRequest.getUser(dni);

        mLandingView.setLoadingIndicator(true);

        call.enqueue(new Callback<ArrayList<UserEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<UserEntity>> call, Response<ArrayList<UserEntity>> response) {
                if (response.isSuccessful()) {

                    if(response.body().size()>0){
                        mLandingView.setLoadingIndicator(false);
                        mLandingView.userProfile(response.body().get(0));
                    }else{
                        mLandingView.setLoadingIndicator(false);
                        mLandingView.showLoadingError("No se encontró ningún resultado");
                    }
                } else {
                    mLandingView.setLoadingIndicator(false);
                    mLandingView.showLoadingError("No se encontró ningún resultado");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserEntity>> call, Throwable t) {
                mLandingView.setLoadingIndicator(false);
                mLandingView.showLoadingError("No se pudo conectar al servidor, revise su conexión a internet");
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void start(int id) {

    }
}
