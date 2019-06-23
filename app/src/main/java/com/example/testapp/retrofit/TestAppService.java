package com.example.testapp.retrofit;

import com.example.testapp.mvp.models.Comment;
import com.example.testapp.mvp.models.CommentPost;
import com.example.testapp.mvp.models.CommentResponse;
import com.example.testapp.mvp.models.LogRegResponse;
import com.example.testapp.mvp.models.Product;
import com.example.testapp.mvp.models.UserPost;

import io.reactivex.Single;
import retrofit2.Response;

public class TestAppService {

    private TestAppApi mTestAppApi;

    public TestAppService(TestAppApi testAppApi){
        mTestAppApi = testAppApi;
    }

    public Single<Product[]> getProducts(){
        return mTestAppApi.getProducts();
    }

    public Single<Comment[]> getComments(int productId){
        return  mTestAppApi.getComments(productId);
    }

    public Single<LogRegResponse> login(UserPost userPost){
        return  mTestAppApi.login(userPost);
    }

    public Single<LogRegResponse> register(UserPost userPost){
        return mTestAppApi.register(userPost);
    }

    public Single<CommentResponse> sendFeedback(int productId, CommentPost feedback, String token){
        return  mTestAppApi.sendFeedback(productId, feedback, token);
    }

}
