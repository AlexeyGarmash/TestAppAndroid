package com.example.testapp.retrofit;

public interface AuthenticateCallbacks {

    void OnAuthenticateSuccess(String message);
    void OnAuthenticateFailed(String error);
}
