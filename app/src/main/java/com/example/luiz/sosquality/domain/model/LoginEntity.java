package com.example.luiz.sosquality.domain.model;

import com.example.luiz.sosquality.R;

/**
 * Created by luiz on 11/7/2016.
 */

public enum  LoginEntity {

    USER(R.color.white, R.layout.view_user),
    CONTACT(R.color.white, R.layout.view_contact),
    ALERGIC(R.color.white, R.layout.view_alergic);

    private int titleResId;
    private int layoutResId;

    LoginEntity(int titleResId, int layoutResId) {
        this.titleResId = titleResId;
        this.layoutResId = layoutResId;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public int getLayoutResId() {
        return layoutResId;
    }
}
