package com.example.testapp.mvp.views;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface FragmentsView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void selectTab(Fragment fragment, int replaceContainer);
}
