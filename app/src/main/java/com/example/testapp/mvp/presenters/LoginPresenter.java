package com.example.testapp.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.testapp.base.BaseApplication;
import com.example.testapp.common.Common;
import com.example.testapp.common.Utils;
import com.example.testapp.mvp.models.Comment;
import com.example.testapp.mvp.models.LogRegResponse;
import com.example.testapp.mvp.models.UserPost;
import com.example.testapp.mvp.views.LoginView;
import com.example.testapp.retrofit.TestAppService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@InjectViewState
public class LoginPresenter extends BasePresenter<LoginView> {

    @Inject
    TestAppService mTestAppService;

    public LoginPresenter(){
        BaseApplication.getsAppComponent().inject(this);
    }

    public void logIn(UserPost userData){
        sendPostLogin(userData);
    }

    private void sendPostLogin(UserPost userData) {
        getViewState().showRefreshing();
        unsubscribeOnDestroy(mTestAppService.login(userData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<LogRegResponse>()
                {
                    @Override
                    public void onSuccess(LogRegResponse logRegResponse) {
                        onLoginGetResponse(logRegResponse, userData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onLoginFailed(e);
                    }
                }));
    }

    private void onLoginFailed(Throwable e) {
        getViewState().hideRefreshing();
        getViewState().showError(e.getMessage());
    }

    private void onLoginGetResponse(LogRegResponse logRegResponse, UserPost user) {
        getViewState().hideRefreshing();
        if(!logRegResponse.getSuccess()){
            onLoginFailed(new Exception(logRegResponse.getMessage()));
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
