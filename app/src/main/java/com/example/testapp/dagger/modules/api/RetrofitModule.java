package com.example.testapp.dagger.modules.api;

import com.example.testapp.common.Common;
import com.example.testapp.retrofit.ApiClient;
import com.example.testapp.retrofit.RefreshTokenClient;
import com.example.testapp.retrofit.TokenAuthenticator;
import com.example.testapp.retrofit.TokenService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    @Singleton
    @ApiClient
    public Retrofit provideRetrofit(@ApiClient Retrofit.Builder builder){
        return builder.baseUrl(Common.BASE_URL).build();
    }

    @Provides
    @Singleton
    @ApiClient
    public  Retrofit.Builder provideRetrofitBuilder(Converter.Factory converterFactory, @ApiClient OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(converterFactory)
                .client(okHttpClient);
    }

    @Provides
    @Singleton
    @ApiClient
    public OkHttpClient.Builder provideOkHttpClientBuilder(TokenAuthenticator tokenAuthenticator){
        return new OkHttpClient.Builder()
                .authenticator(tokenAuthenticator);
    }



    @Provides
    @Singleton
    @ApiClient
    public OkHttpClient provideOkHttpClient(@ApiClient OkHttpClient.Builder builder){
        return builder.build();
    }


    @Provides
    @Singleton
    public Converter.Factory provideConverterFactory(){
        return GsonConverterFactory.create();
    }



    @Provides
    @Singleton
    @RefreshTokenClient
    public Retrofit provideRetrofitToken(@RefreshTokenClient Retrofit.Builder builder){
        return builder.baseUrl(Common.BASE_URL).build();
    }


    @Provides
    @Singleton
    @RefreshTokenClient
    public  Retrofit.Builder provideRetrofitBuilderToken(Converter.Factory converterFactory){
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(converterFactory);
    }







}
