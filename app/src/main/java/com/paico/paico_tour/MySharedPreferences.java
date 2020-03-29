package com.paico.paico_tour;

import android.content.Context;
import android.content.SharedPreferences;

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

}
