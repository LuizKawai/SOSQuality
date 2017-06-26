package com.example.luiz.sosquality.presentation.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.luiz.sosquality.domain.model.ContactEntity;
import com.example.luiz.sosquality.domain.remote.ServiceGeneratorSimple;
import com.example.luiz.sosquality.domain.remote.request.ContactRequest;
import com.example.luiz.sosquality.presentation.contracts.ContractContactProfile;
import com.example.luiz.sosquality.presentation.presenter.commons.CommunicatePresenterContactProfileItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by luiz on 12/13/2016.
 */

public class ContactProfilePresenter implements ContractContactProfile.Presenter, CommunicatePresenterContactProfileItem {

    private final ContractContactProfile.View mNewsView;
    private Context context;
    private boolean mFirstLoad = true;
    private String next;

    public ContactProfilePresenter(@NonNull ContractContactProfile.View mNewsView, @NonNull Context context) {
        this.context = checkNotNull(context, "context cannot be null!");
        this.mNewsView = checkNotNull(mNewsView, "newsView cannot be null!");
        this.mNewsView.setPresenter(this);
    }

    @Override
    public void llamadaContactos(boolean forceUpdate, int userID) {
        llamadaContactos(forceUpdate || mFirstLoad, true, userID);
        mFirstLoad = false;
    }

    private void llamadaContactos(boolean forceUpdate, final boolean showLoadingUI, int userID) {

        if (showLoadingUI) {
            mNewsView.setLoadingIndicator(true);
        }
        final ContactRequest newContactRequest =
                ServiceGeneratorSimple.createService(ContactRequest.class);

        Call<ArrayList<ContactEntity>> call = newContactRequest.getContacts(String.valueOf(userID));

        call.enqueue(new Callback<ArrayList<ContactEntity>>() {
            @Override
            public void onResponse(Call<ArrayList<ContactEntity>> call, Response<ArrayList<ContactEntity>> response) {
                if (response.isSuccessful()){
                    mNewsView.showContacts(response.body());

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
            public void onFailure(Call<ArrayList<ContactEntity>> call, Throwable t) {
                if (!mNewsView.isActive()) {
                    return;
                }
                if (showLoadingUI) {
                    mNewsView.setLoadingIndicator(false);
                }
                mNewsView.showMessage("No se pudo cargar la lista de contactos, revise su conexión a internet");
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void start(int id) {
        llamadaContactos(false, id);
    }

    @Override
    public void clickContactItem(ContactEntity contactEntity) {
        mNewsView.makeCall(contactEntity);
    }
}
