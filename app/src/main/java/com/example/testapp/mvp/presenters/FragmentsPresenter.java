package com.example.testapp.mvp.presenters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.arellomobile.mvp.InjectViewState;
import com.example.testapp.mvp.views.FragmentsView;

@InjectViewState
public class FragmentsPresenter extends BasePresenter<FragmentsView> {

    public FragmentsPresenter(){}


    public void selectTab(Fragment fragment, int replaceContainer) {
        getViewState().selectTab(fragment, replaceContainer);
    }
}
