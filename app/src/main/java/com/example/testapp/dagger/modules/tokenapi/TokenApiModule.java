package com.example.testapp.dagger.modules.tokenapi;

import com.example.testapp.dagger.modules.api.RetrofitModule;
import com.example.testapp.retrofit.RefreshTokenClient;
import com.example.testapp.retrofit.TokenApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {RetrofitModule.class})
public class TokenApiModule {

    @Provides
    @Singleton
    public TokenApi provideTokenApi(@RefreshTokenClient  Retrofit retrofit){
        return  retrofit.create(TokenApi.class);
    }
}
