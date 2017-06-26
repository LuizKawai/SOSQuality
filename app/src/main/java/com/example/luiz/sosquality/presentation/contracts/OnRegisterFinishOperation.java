package com.example.luiz.sosquality.presentation.contracts;

/**
 * Created by luiz on 11/14/2016.
 */

public interface OnRegisterFinishOperation {
    void userNameError();
    void userDNIError();
    void userAgeError();
    void successRegister();
}
