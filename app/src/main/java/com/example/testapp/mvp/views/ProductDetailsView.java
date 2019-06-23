package com.example.testapp.mvp.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.testapp.mvp.models.Product;

public interface ProductDetailsView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void showItem(Product product);

    void showMessage(String message);
}
