package com.example.testapp.mvp.views;

import com.arellomobile.mvp.MvpView;

public interface BaseLceView  extends MvpView {
    void showError(String message);
    void hideError();
    void onStartLoading();
    void onFinishLoading();
    void showRefreshing();
    void hideRefreshing();
}
