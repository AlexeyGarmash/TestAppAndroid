package com.example.testapp.mvp.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.testapp.base.BaseApplication;
import com.example.testapp.common.Common;
import com.example.testapp.common.Utils;
import com.example.testapp.mvp.models.CommentPost;
import com.example.testapp.mvp.models.CommentResponse;
import com.example.testapp.mvp.models.Product;
import com.example.testapp.mvp.views.ProductDetailsView;
import com.example.testapp.retrofit.TestAppService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProductDetailsPresenter extends BasePresenter<ProductDetailsView> {

    private Product mProduct;

    @Inject
    TestAppService mTestAppService;

    public ProductDetailsPresenter() {
        BaseApplication.getsAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach(){
        super.onFirstViewAttach();
        showProductOrFail();
    }

    private void showProductOrFail() {
        if (mProduct == null) {
            showError("Product is null");
        } else {
            showItem(mProduct);
        }
    }

    private void showError(String message){
        getViewState().showMessage(message);
    }

    private void showItem(Product product){
        getViewState().showItem(product);
    }


    public void setmProduct(Product product){
        mProduct = product;
    }


    public void sendFeedback(CommentPost feedback){
        sendPostFeedback(feedback);
    }

    private void sendPostFeedback(CommentPost feedback) {
        Log.i("SEND COMMENT::::::::::", " SEND COMMENT STARTED");

        String token = Utils.getUserData(Common.KEY_TOKEN);
        unsubscribeOnDestroy(mTestAppService.sendFeedback(mProduct.getId(), feedback, Common.tokenBase + token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CommentResponse>()
                {
                    @Override
                    public void onSuccess(CommentResponse response) {
                        Log.i("SEND COMMENT::::::::::", " SEND COMMENT SUCCESS CALLBACK");
                        onSendSuccess(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("SEND COMMENT::::::::::", " SEND COMMENT FAIL CALLBACK");
                        onSendFailed(e);
                    }
                }));
        Log.i("SEND COMMENT::::::::::", " SEND COMMENT ENDEEEEEEED");
    }

    private void onSendFailed(Throwable e) {
        getViewState().showMessage("From presenter: " + e.getMessage());
    }

    private void onSendSuccess(CommentResponse response) {
        if(!response.getSuccess()) {
            onSendFailed(new Exception("Send feedback failed"));
        }
        else{
            getViewState().showMessage("Success!");
        }
    }
}
