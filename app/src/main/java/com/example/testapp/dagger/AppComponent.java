package com.example.testapp.dagger;

import android.content.Context;

import com.example.testapp.dagger.modules.ContextModule;
import com.example.testapp.dagger.modules.api.TestAppServiceModule;
import com.example.testapp.dagger.modules.tokenapi.TokenServiceModule;
import com.example.testapp.mvp.presenters.CommentsPresenter;
import com.example.testapp.mvp.presenters.LoginPresenter;
import com.example.testapp.mvp.presenters.ProductDetailsPresenter;
import com.example.testapp.mvp.presenters.ProductsPresenter;
import com.example.testapp.mvp.presenters.RegisterPresenter;
import com.example.testapp.retrofit.TestAppService;
import com.example.testapp.retrofit.TokenAuthenticator;
import com.example.testapp.retrofit.TokenService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContextModule.class, TestAppServiceModule.class, TokenServiceModule.class})
public interface AppComponent {

    Context getContext();
    TestAppService getService();
    //TokenService getTokenService();

    void inject(ProductsPresenter productsPresenter);

    void inject(CommentsPresenter commentsPresenter);

    void inject(LoginPresenter loginPresenter);

    void inject(ProductDetailsPresenter productDetailsPresenter);

    void inject(RegisterPresenter registerPresenter);

    //void inject(TokenAuthenticator tokenAuthenticator);

    //TODO: inject
}
