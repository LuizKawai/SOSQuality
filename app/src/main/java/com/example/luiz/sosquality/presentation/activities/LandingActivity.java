package com.example.luiz.sosquality.presentation.activities;

import android.os.Bundle;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.presentation.core.BaseActivity;
import com.example.luiz.sosquality.presentation.fragments.LandingFragment;
import com.example.luiz.sosquality.utils.ActivityUtils;

public class LandingActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);

        LandingFragment fragment = (LandingFragment) getSupportFragmentManager()
                .findFragmentById(R.id.layout_content_frame);

        if (fragment == null) {
            fragment = LandingFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.layout_content_frame);
        }
    }
}
