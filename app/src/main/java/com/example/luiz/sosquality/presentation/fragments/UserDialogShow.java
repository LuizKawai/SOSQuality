package com.example.luiz.sosquality.presentation.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.utils.Util_Fonts;

import butterknife.BindView;

public class UserDialogShow extends AlertDialog {

    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_dni) TextView tvDni;
    @BindView(R.id.tv_height) TextView tvHeight;
    @BindView(R.id.tv_weight) TextView tvWeight;
    @BindView(R.id.tv_imc) TextView tvImc;

    private UserEntity userEntity = null;

    public UserDialogShow(Context context, UserEntity userEntity) {
        super(context);
        this.userEntity = userEntity;
        initDialog(userEntity);
    }

    private void initDialog(UserEntity user) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.fragment_layout_user_profile, null);
        setView(view);

        tvName.setText(user.getNombreUsuario());
        tvDni.setText(user.getDNIUsuario());
        tvHeight.setText(String.valueOf(user.getTalla()));
        tvWeight.setText(String.valueOf(user.getPeso()));
        tvImc.setText(String.valueOf(userEntity.getPeso()/Math.pow(userEntity.getTalla(), 2)));
/*
        tvName.setTypeface(Util_Fonts.setFontLight(getContext()));
        tvDni.setTypeface(Util_Fonts.setFontLight(getContext()));
        tvHeight.setTypeface(Util_Fonts.setFontLight(getContext()));
        tvWeight.setTypeface(Util_Fonts.setFontLight(getContext()));
        tvImc.setTypeface(Util_Fonts.setFontLight(getContext()));
  */  }
}
