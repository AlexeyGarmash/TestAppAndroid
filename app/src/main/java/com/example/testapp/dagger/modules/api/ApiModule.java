package com.example.testapp.dagger.modules.api;

import com.example.testapp.retrofit.ApiClient;
import com.example.testapp.retrofit.TestAppApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module (includes = {RetrofitModule.class})
public class ApiModule {

    @Provides
    @Singleton
    public TestAppApi provideTestAppApi(@ApiClient Retrofit retrofit){
        return  retrofit.create(TestAppApi.class);
    }
}
