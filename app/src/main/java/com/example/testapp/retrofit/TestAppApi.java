package com.example.testapp.retrofit;

import com.example.testapp.mvp.models.Comment;
import com.example.testapp.mvp.models.CommentPost;
import com.example.testapp.mvp.models.CommentResponse;
import com.example.testapp.mvp.models.LogRegResponse;
import com.example.testapp.mvp.models.Product;
import com.example.testapp.mvp.models.UserPost;

import javax.inject.Qualifier;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface TestAppApi {



    @GET(Paths.ALL_PRODUCTS)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Single<Product[]> getProducts();


    @GET("/api/reviews/{productId}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Single<Comment[]> getComments(@Path("productId") int productId);

    @POST(Paths.LOGIN)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Single<LogRegResponse> login(@Body UserPost userPost);


    @POST(Paths.REGISTER)
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Single<LogRegResponse> register(@Body UserPost userPost);


    @POST("/api/reviews/{productId}")
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    Single<CommentResponse> sendFeedback(@Path("productId") int productId, @Body CommentPost feedback, @Header("Authorization") String token);

}
