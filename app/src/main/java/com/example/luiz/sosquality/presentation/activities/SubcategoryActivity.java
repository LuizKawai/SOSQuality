package com.example.luiz.sosquality.presentation.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.luiz.sosquality.R;
import com.example.luiz.sosquality.domain.model.CategoryEntity;
import com.example.luiz.sosquality.presentation.core.BaseActivity;
import com.example.luiz.sosquality.presentation.fragments.SubcategoryFragment;
import com.example.luiz.sosquality.utils.ActivityUtils;

public class SubcategoryActivity extends BaseActivity {

    public static final String EXTRA_NEWS = "NEW_PARAMETER";

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
        CategoryEntity categoryEntity = (CategoryEntity) getIntent().getSerializableExtra(EXTRA_NEWS);

        SubcategoryFragment fragment = (SubcategoryFragment) getSupportFragmentManager()
                .findFragmentById(R.id.layout_content_frame);
        ab.setTitle(categoryEntity.getNombre());

        if (fragment == null) {
            fragment = SubcategoryFragment.newInstance(categoryEntity);

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
