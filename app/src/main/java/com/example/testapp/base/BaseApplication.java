package com.example.testapp.base;

import android.app.Application;

import com.example.testapp.dagger.AppComponent;
import com.example.testapp.dagger.DaggerAppComponent;
import com.example.testapp.dagger.modules.ContextModule;

public class BaseApplication extends Application {

    private static AppComponent sAppComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        sAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public static AppComponent getsAppComponent(){
        return sAppComponent;
    }
}
