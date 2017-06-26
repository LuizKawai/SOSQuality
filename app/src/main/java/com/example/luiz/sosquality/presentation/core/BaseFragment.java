package com.example.luiz.sosquality.presentation.core;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by luiz on 11/10/2016.
 */

public class BaseFragment extends Fragment {

    protected void next(Activity context, Bundle bundle, Class<?> activity, boolean destroy) {
        Intent intent= new Intent(context,activity);
        if(bundle!=null){intent.putExtras(bundle);}
        startActivity(intent);
        if(destroy)context.finish();
    }
    protected void nextAll(Activity context, Bundle bundle, Class<?> activity, boolean destroyall) {
        Intent intent= new Intent(context,activity);
        if(bundle!=null){intent.putExtras(bundle);}
        if(destroyall)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void hideKeyboard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    protected void initView(){}
}
