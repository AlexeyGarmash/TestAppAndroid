package com.example.testapp.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.testapp.base.BaseApplication;
import com.example.testapp.common.Common;
import com.example.testapp.common.Utils;
import com.example.testapp.mvp.models.LogRegResponse;
import com.example.testapp.mvp.models.UserPost;
import com.example.testapp.mvp.views.RegisterView;
import com.example.testapp.retrofit.TestAppService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class RegisterPresenter extends BasePresenter<RegisterView> {

    @Inject
    TestAppService mTestAppService;

    public RegisterPresenter(){
        BaseApplication.getsAppComponent().inject(this);
    }

    public void register(UserPost userData){
        sendPostRegister(userData);
    }

    private void sendPostRegister(UserPost userData) {
        getViewState().showRefreshing();
        unsubscribeOnDestroy(mTestAppService.register(userData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LogRegResponse>()
                {
                    @Override
                    public void onSuccess(LogRegResponse logRegResponse) {
                        onRegisterGetResponse(logRegResponse, userData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onRegisterFailed(e);
                    }
                }));
    }

    private void onRegisterFailed(Throwable e) {
        getViewState().hideRefreshing();
        getViewState().showError(e.getMessage());
    }

    private void onRegisterGetResponse(LogRegResponse logRegResponse, UserPost user) {
        getViewState().hideRefreshing();
        if(!logRegResponse.getSuccess()){
            onRegisterFailed(new Exception(logRegResponse.getMessage()));
        }
        else {
            saveUserData(logRegResponse, user);
            getViewState().goToMainActivity();
        }
    }

    private void saveUserData(LogRegResponse logRegResponse, UserPost user) {
        Utils.saveUserDataToPrefs(Common.KEY_USERNAME, user.getUsername());
        Utils.saveUserDataToPrefs(Common.KEY_PASSWORD, user.getPassword());
        Utils.saveUserDataToPrefs(Common.KEY_TOKEN, logRegResponse.getToken());
    }
}
