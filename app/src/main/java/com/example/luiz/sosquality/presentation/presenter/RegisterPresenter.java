package com.example.luiz.sosquality.presentation.presenter;

import android.content.Context;

import com.example.luiz.sosquality.domain.model.AlergyEntity;
import com.example.luiz.sosquality.domain.model.ContactEntity;
import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.domain.remote.ServiceGeneratorSimple;
import com.example.luiz.sosquality.domain.remote.request.RegisterRequest;
import com.example.luiz.sosquality.domain.remote.request.UserRequest;
import com.example.luiz.sosquality.presentation.contracts.ContractRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter implements ContractRegister.Presenter {

    private boolean  b1,b2,b3;
    Context context;

    public RegisterPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void registroContacto(int Usuario_DNIUsuario, String NombreContacto, String NumeroTelef) {
        final RegisterRequest newContactRequest = ServiceGeneratorSimple.createService(RegisterRequest.class);

        ContactEntity contactEntity = new ContactEntity(Usuario_DNIUsuario, NombreContacto, NumeroTelef);

        Call<ContactEntity> contactEntityCall = newContactRequest.createContact(contactEntity);

        contactEntityCall.enqueue(new Callback<ContactEntity>() {
            @Override
            public void onResponse(Call<ContactEntity> call, Response<ContactEntity> response) {
                if (response.isSuccessful()){
                    b1=true;
                } else {
                    b1=false;
                }
            }
            @Override
            public void onFailure(Call<ContactEntity> call, Throwable t) {

            }
        });
    }

    @Override
    public void registroAlergia(int Usuario_DNIUsuario, String NombreAlergia, String Medicacion) {
        final RegisterRequest newContactRequest = ServiceGeneratorSimple.createService(RegisterRequest.class);

        AlergyEntity alergyEntity = new AlergyEntity(Usuario_DNIUsuario, NombreAlergia, Medicacion);

        Call<AlergyEntity> alergyEntityCall = newContactRequest.createAlergy(alergyEntity);

        alergyEntityCall.enqueue(new Callback<AlergyEntity>() {
            @Override
            public void onResponse(Call<AlergyEntity> call, Response<AlergyEntity> response) {
                if (response.isSuccessful()){
                    b2=true;
                } else {
                    b2=false;
                }
            }
            @Override
            public void onFailure(Call<AlergyEntity> call, Throwable t) {

            }
        });
    }

    @Override
    public void regitroUsuario(int dni, String nombre, float peso, float talla, String sexo, String tipoSangre, String mensajePredeterminado) {
        final UserRequest newUserRequest = ServiceGeneratorSimple.createService(UserRequest.class);

        UserEntity userEntity = new UserEntity(dni, nombre, peso, talla, sexo, tipoSangre, "");

        Call<UserEntity> userEntityCall = newUserRequest.createUser(userEntity);

        userEntityCall.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if (response.isSuccessful()){
                    b3=true;
                } else {
                    b3=false;
                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
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
