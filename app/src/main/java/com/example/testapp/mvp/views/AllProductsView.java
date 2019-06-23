package com.example.testapp.mvp.views;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.testapp.mvp.models.Product;

import java.util.ArrayList;

public interface AllProductsView extends BaseLceView{

    @StateStrategyType(AddToEndStrategy.class)
    void addToList(ArrayList<Product> products);

    @StateStrategyType(SingleStateStrategy.class)
    void clearData();
}
