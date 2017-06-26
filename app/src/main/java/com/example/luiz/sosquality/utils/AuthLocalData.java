package com.example.luiz.sosquality.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class AuthLocalData {
    public static final String PREFERENCE_NAME = "Uluck";
    public static int PRIVATE_MODE = 0;

    /**
     USUARIO DATA SESSION - JSON
     */
    public static final String USER_TOKEN = "user_token";
    public static final String USER_JSON = "user_json";
    public static final String IS_LOGIN = "user_login";

    public static final String PROPERTY_REG_ID="reg_id";
    /**
     Last location
     */
    public static final String LOCATION = "location";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public AuthLocalData(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public boolean isLogin()  {
        return preferences.getBoolean(IS_LOGIN, false);
    }

    public void openSession() {
        editor.putBoolean(IS_LOGIN, true);

        editor.commit();
    }

    public boolean hasTokenNotification(){
        String redid = preferences.getString(PROPERTY_REG_ID,null);
        return redid != null;
    }

    public void setTokenNotification(String token) {
        if(token!=null){
            if(!token.isEmpty()){
                editor.putString(PROPERTY_REG_ID, token);
            }
            editor.commit();
        }

    }

    public String getTokenNotification()  {
        return preferences.getString(PROPERTY_REG_ID, "");
    }

    public void closeSession() {
        editor.putBoolean(IS_LOGIN, false);

    }

    public String getUserToken() {
        if (isLogin()) {
            return preferences.getString(USER_TOKEN, "");
        } else {
            return "";
        }
    }
}

