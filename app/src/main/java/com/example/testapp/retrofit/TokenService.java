package com.example.testapp.retrofit;

import com.example.testapp.mvp.models.LogRegResponse;
import com.example.testapp.mvp.models.UserPost;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;

public class TokenService {

    private TokenApi mTokenApi;


    public TokenService(TokenApi tokenApi){
        mTokenApi = tokenApi;
    }


    public Call<LogRegResponse> refreshToken(UserPost user){
        return  mTokenApi.refreshToken(user);
    }
}
