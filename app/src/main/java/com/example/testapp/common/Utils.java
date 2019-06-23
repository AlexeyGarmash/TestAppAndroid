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

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Locale;


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

    public static String convertDateTime(String inputDateTime){
        DateTimeFormatter isoInstantFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateTimeFormatter isoInstantFormatterWithMs = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy HH:mm:ss", Locale.US);
        LocalDateTime dateTime;
        try{
            dateTime = LocalDateTime.parse(inputDateTime, isoInstantFormatterWithMs);
        } catch (Exception ex){
            dateTime = LocalDateTime.parse(inputDateTime, isoInstantFormatter);
        }
        return dateTime.format(outputFormatter);
    }


}
