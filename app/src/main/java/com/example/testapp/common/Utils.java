package com.example.testapp.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.testapp.R;
import com.example.testapp.base.BaseApplication;
import com.example.testapp.mvp.models.LogRegResponse;

import java.util.ArrayList;



public class Utils {

    public static <T> ArrayList<T> arrayToList(T[] array){
        ArrayList<T> list = new ArrayList<>();
        for (T item : array){
            list.add(item);
        }
        return list;
    }

    public static void setImageByURL(ImageView view, int width, int height, String imgURL){
        Glide.with(BaseApplication.getsAppComponent().getContext())
                .load(imgURL)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .override(width,height))
                .into(view);
    }

    public static boolean saveUserDataToPrefs(String dataKey, String data){
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(BaseApplication.getsAppComponent().getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(dataKey, data);
        editor.apply();
        return true;
    }

    public static String getUserData(String dataKey){
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(BaseApplication.getsAppComponent().getContext());
        return preferences.getString(dataKey, null);
    }


}
