package com.example.luiz.sosquality.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.domain.remote.ServiceGeneratorSimple;
import com.example.luiz.sosquality.domain.remote.request.UserRequest;
import com.example.luiz.sosquality.presentation.contracts.ContractProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.valueOf;

public class ProfilePresenter implements ContractProfile.Presenter {

    private final ContractProfile.View mNewsView;
    private Context context;
    private boolean mFirstLoad = true;
    private String next;


    public ProfilePresenter(@NonNull ContractProfile.View mNewsView, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.mNewsView = checkNotNull(mNewsView, "newsView cannot be null!");
        this.mNewsView.setPresenter(this);
    }

    @Override
    public void loadProfile(boolean forceUpdate, int userDNI) {
        loadProfile(forceUpdate || mFirstLoad, true, userDNI);
        mFirstLoad = false;
    }

    @Override
    public void loadUserInformation(UserEntity userEntity) {
        if (!valueOf(userEntity.getDNIUsuario()).equals(null)){
            mNewsView.showUserInformation(userEntity);
        }
    }

    @Override
    public void loadUserContactInformation(String userID) {

    }

    @Override
    public void loadUserAlergicInformation(String userID) {

    }

    private void loadProfile(boolean forceUpdate,final boolean showLoadingUI,int userDNI){

    }

    @Override
    public void start() {

    }

    @Override
    public void start(int userDNI) {
        loadProfile(false, userDNI);
    }
}
