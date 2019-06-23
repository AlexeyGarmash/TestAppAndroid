package com.example.testapp.retrofit;

import com.example.testapp.mvp.models.LogRegResponse;
import com.example.testapp.mvp.models.UserPost;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TokenApi {
    @POST(Paths.LOGIN)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Call<LogRegResponse> refreshToken(@Body UserPost userPost);
}
