package com.example.luiz.sosquality.presentation.activities;

import android.os.Bundle;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.presentation.core.BaseActivity;
import com.example.luiz.sosquality.presentation.fragments.FirstAidFragment;
import com.example.luiz.sosquality.presentation.fragments.LandingFragment;
import com.example.luiz.sosquality.utils.ActivityUtils;

/**
 * Created by luiz on 11/22/2016.
 */

public class FirstAidActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);

        FirstAidFragment fragment = (FirstAidFragment) getSupportFragmentManager()
                .findFragmentById(R.id.layout_content_frame);

        if (fragment == null) {
            fragment = FirstAidFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),fragment,R.id.layout_content_frame);

        }
    }
}
