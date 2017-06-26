package com.example.luiz.sosquality.presentation.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luiz.sosquality.domain.model.LoginEntity;

/**
 * Created by luiz on 11/7/2016.
 */

public class CustomPagerAdapter extends PagerAdapter {

    private Context context;

    public CustomPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LoginEntity loginEntity = LoginEntity.values()[position];
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(loginEntity.getLayoutResId(), collection, false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return LoginEntity.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        LoginEntity customPagerEnum = LoginEntity.values()[position];
        return context.getString(customPagerEnum.getTitleResId());
    }
}
