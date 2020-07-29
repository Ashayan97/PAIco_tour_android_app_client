package com.paico.paico_tour;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.paico.paico_tour.object_classes.User;

public class MySharedPreferences {
    private static MySharedPreferences instance;

    public static MySharedPreferences getInstance(Context context) {
        if (instance == null)
            instance = new MySharedPreferences(context);
        return instance;
    }

    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;

    private MySharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getAccessKey(){

        return sharedPreferences.getString("acc_key",null);
    }

    public void setAccessKey(String str){
        editor.putString("acc_key",str);
        editor.apply();
    }


    public User getUserInfo(){

        Gson gson=new Gson();
        String str= sharedPreferences.getString("user_info",null);
        User user = gson.fromJson(str,User.class);
        return user;
    }


    public void setUserInfo(User user){
        Gson gson = new Gson();
        String strInfo = gson.toJson(user);
        editor.putString("user_info",strInfo);
        editor.apply();
    }

}
