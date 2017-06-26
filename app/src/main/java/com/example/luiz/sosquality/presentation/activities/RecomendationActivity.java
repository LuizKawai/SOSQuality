package com.example.luiz.sosquality.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.SubcategoryEntity;
import com.example.luiz.sosquality.presentation.core.BaseActivity;
import com.example.luiz.sosquality.presentation.fragments.RecomendationFragment;
import com.example.luiz.sosquality.utils.ActivityUtils;

public class RecomendationActivity extends BaseActivity {

    public static final String SUBCATEGORY_INTENT = "NEW_SUBCATEGORY";

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

        // Get the requested task id
        SubcategoryEntity subcategoryEntity = (SubcategoryEntity) getIntent().getSerializableExtra(SUBCATEGORY_INTENT);

        RecomendationFragment fragment = (RecomendationFragment) getSupportFragmentManager()
                .findFragmentById(R.id.layout_content_frame);
        ab.setTitle(subcategoryEntity.getNombre());

        if (fragment == null) {
            fragment = RecomendationFragment.newInstance(subcategoryEntity);

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
