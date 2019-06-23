package com.example.testapp.retrofit;

import android.util.Log;
import android.widget.Toast;

import com.example.testapp.activities.MainActivity;
import com.example.testapp.base.BaseApplication;
import com.example.testapp.common.Common;
import com.example.testapp.common.Utils;
import com.example.testapp.mvp.models.LogRegResponse;
import com.example.testapp.mvp.models.UserPost;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

public class TokenAuthenticator implements Authenticator {


    @Inject
    TokenService mTokenService;

    private CompositeDisposable compositeDisposable;

    public AuthenticateCallbacks mAuthenticateCallbacks;

    public TokenAuthenticator(TokenService tokenService) {
        mTokenService =tokenService;
        //BaseApplication.getsAppComponent().inject(this);
        compositeDisposable = new CompositeDisposable();
    }
    Request request;
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        request = null;
        String username = Utils.getUserData(Common.KEY_USERNAME);
        String password = Utils.getUserData(Common.KEY_PASSWORD);
        Log.i("AUTH METHOD::::::::::", "WE ARE IN START");

        Log.i("AUTH METHOD::::::::::", "NAME: " + username + " PASS: " + password);
        if(username != null && password != null){
            Log.i("AUTH METHOD::::::::::", "WE ARE IN IF STATEMENT");
            Call<LogRegResponse> callToken = mTokenService.refreshToken(new UserPost(username, password));
            retrofit2.Response<LogRegResponse> tokenResponse = callToken.execute();

            if(tokenResponse == null){
                Log.i("AUTH METHOD::::::::::", "TOKEN RESPONSE NULLLLL");
                return  null;
            }
            if(tokenResponse.isSuccessful()) {
                Log.i("AUTH METHOD::::::::::", "WE ARE IN SUCCESS");
                String token = tokenResponse.body().getToken();
                Utils.saveUserDataToPrefs(Common.KEY_TOKEN , token);
                //mAuthenticateCallbacks.OnAuthenticateSuccess("Success repair session!");
                request = response.request().newBuilder()
                        .header("Authorization", Common.tokenBase + token)
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .build();
            }
            else {
                Utils.saveUserDataToPrefs(Common.KEY_TOKEN ,null);
                Log.e("AUTH METHOD::::::::::", "WE ARE IN FAIIIIIIILED");
            }
        }
        //compositeDisposable.clear();
        return request;
    }

    private void addCompositeDisposable(Disposable disposable){
        compositeDisposable.add(disposable);
    }
}
