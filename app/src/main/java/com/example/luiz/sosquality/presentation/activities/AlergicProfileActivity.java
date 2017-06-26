package com.example.luiz.sosquality.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.UserEntity;
import com.example.luiz.sosquality.presentation.core.BaseActivity;
import com.example.luiz.sosquality.presentation.fragments.ContactProfileFragment;
import com.example.luiz.sosquality.utils.ActivityUtils;

import static com.example.luiz.sosquality.presentation.activities.ContactProfileActivity.CONTACT_LIST;

/**
 * Created by luiz on 12/13/2016.
 */

public class AlergicProfileActivity extends BaseActivity {

    public static final String ALERGIC_LIST = "NEW_ALERGIES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_back);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);

        UserEntity userEntity = (UserEntity) getIntent().getSerializableExtra(ALERGIC_LIST);

        ContactProfileFragment fragment = (ContactProfileFragment) getSupportFragmentManager()
                .findFragmentById(R.id.layout_content_frame);
        ab.setTitle("Alergias");

        if (fragment == null) {
            fragment = ContactProfileFragment.newInstance(userEntity);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.layout_content_frame);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
