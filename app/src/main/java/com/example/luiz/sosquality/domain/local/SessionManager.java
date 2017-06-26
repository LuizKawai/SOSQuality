package com.example.luiz.sosquality.domain.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.luiz.sosquality.domain.model.UserEntity;
import com.google.gson.Gson;

import static com.example.luiz.sosquality.utils.AuthLocalData.PREFERENCE_NAME;
import static com.example.luiz.sosquality.utils.AuthLocalData.PRIVATE_MODE;

public class SessionManager {

/*    public static final String PREFERENCE_NAME = "check_in";
    public static int PRIVATE_MODE = 0;

    /**
     USUARIO DATA SESSION - JSON
     */
/*    public static final String USER_TOKEN = "user_token";
    public static final String USER_JSON = "user_json";
    public static final String IS_LOGIN = "user_login";


    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        preferences = this.context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public boolean isLogin()  {
        return preferences.getBoolean(IS_LOGIN, false);
    }


    public void openSession(AccessTokenEntity token, UserEntity userEntity) {
        editor.putBoolean(IS_LOGIN, true);
        if(token!=null){
            String tok= new Gson().toJson(token);
            editor.putString(USER_TOKEN, tok);
        }
        if(userEntity!=null){
            Gson gson = new Gson();
            String user= gson.toJson(userEntity);
            editor.putString(USER_JSON, user);
        }

        editor.commit();

    }




    public void setUser(UserEntity user_dto) {

        if(user_dto!=null){
            Gson gson = new Gson();
            String user= gson.toJson(user_dto);
            editor.putString(USER_JSON, user);
        }

        editor.commit();

    }

    public void closeSession() {
        editor.putBoolean(IS_LOGIN, false);
        editor.putString(USER_TOKEN, null);
        editor.putString(USER_JSON, null);
        editor.commit();

    }



    public UserEntity getUserEntity(){

        if (isLogin()) {
            String userData = preferences.getString(USER_JSON, null);
            UserEntity user_dto = new Gson().fromJson(userData, UserEntity.class);
            return user_dto;
        } else {
            return null;
        }


    }


    public AccessTokenEntity getUserToken() {
        if (isLogin()) {
            String token =preferences.getString(USER_TOKEN, "");
            AccessTokenEntity accessTokenEntity = new Gson().fromJson(token,AccessTokenEntity.class);
            return accessTokenEntity;
        } else {
            return null;
        }
    }
*/
}
