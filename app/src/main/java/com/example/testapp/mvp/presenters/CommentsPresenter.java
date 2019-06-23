package com.example.testapp.mvp.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.example.testapp.base.BaseApplication;
import com.example.testapp.common.Utils;
import com.example.testapp.dagger.AppComponent;
import com.example.testapp.mvp.models.Comment;
import com.example.testapp.mvp.models.Product;
import com.example.testapp.mvp.views.CommentsView;
import com.example.testapp.retrofit.Paths;
import com.example.testapp.retrofit.TestAppService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class CommentsPresenter extends BasePresenter<CommentsView> {

    @Inject
    TestAppService mTestAppService;

    private Product mProduct;

    public CommentsPresenter(){
        BaseApplication.getsAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadComments();
    }

    public void loadComments() {
        getCommentsFromApi(mProduct.getId());
    }

    private void getCommentsFromApi(int productId) {
        getViewState().showError("Product ID:" + productId);
        getViewState().showRefreshing();
        unsubscribeOnDestroy(mTestAppService.getComments(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Comment[]>()
                {

                    @Override
                    public void onSuccess(Comment[] comments) {
                        getViewState().hideRefreshing();
                        onLoadingSuccess(comments);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().hideRefreshing();
                        onLoadingFailed(e);
                    }
                }));
    }

    public void setmProduct(Product product){
        mProduct = product;
    }

    private void onLoadingSuccess(Comment[] comments){
        getViewState().showComments(Utils.arrayToList(comments));
    }

    private void onLoadingFailed(Throwable throwable){
        getViewState().showError(throwable.getMessage());
    }
}
