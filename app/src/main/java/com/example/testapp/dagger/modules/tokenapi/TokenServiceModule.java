package com.example.testapp.dagger.modules.tokenapi;

import com.example.testapp.retrofit.TokenApi;
import com.example.testapp.retrofit.TokenAuthenticator;
import com.example.testapp.retrofit.TokenService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {TokenApiModule.class})
public class TokenServiceModule {

    @Provides
    @Singleton
    public TokenService provideTokenService(TokenApi api){
        return new TokenService(api);
    }

    @Provides
    @Singleton
    TokenAuthenticator provideTokenAuthenticator(TokenService mApi){
        return new TokenAuthenticator(mApi);
    }
}
