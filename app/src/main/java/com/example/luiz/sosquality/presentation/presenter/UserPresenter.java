package com.example.luiz.sosquality.presentation.presenter;

import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.domain.remote.ServiceGeneratorSimple;
import com.example.luiz.sosquality.domain.remote.request.UserRequest;
import com.example.luiz.sosquality.presentation.contracts.ContractUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter implements ContractUser.Presenter {

    @Override
    public void start() {

    }

    @Override
    public void start(int id) {

    }


    public void regitroUsuario(int dni, String nombre, float peso, float talla, String sexo,
                               String tipoSangre, String mensajePredeterminado) {

        final UserRequest newUserRequest = ServiceGeneratorSimple.createService(UserRequest.class);

        Call<UserEntity> userEntityCall = newUserRequest.createUser(new UserEntity(dni,
                nombre,
                peso,
                talla,
                sexo,
                tipoSangre,
                mensajePredeterminado
            )
        );

        userEntityCall.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {

                if (response.isSuccessful()){
                    //UserEntity newUserEntity = response.body();
                    //UserEntity userEntity = response.body();
                } else {

                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
            }
        });

    }

    @Override
    public void actualizarUsuario(int dni, String nombre, float peso, float talla, String sexo, String tipoSangre, String mensajePredeterminado) {

    }

    @Override
    public void buscarUsuario(String dni) {

    }
}
