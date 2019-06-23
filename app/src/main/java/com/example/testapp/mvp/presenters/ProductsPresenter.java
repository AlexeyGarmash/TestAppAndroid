package com.example.testapp.mvp.presenters;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.example.testapp.base.BaseApplication;
import com.example.testapp.common.Utils;
import com.example.testapp.mvp.models.Product;
import com.example.testapp.mvp.views.AllProductsView;
import com.example.testapp.retrofit.TestAppService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProductsPresenter extends BasePresenter<AllProductsView> {

    @Inject
    TestAppService mTestAppService;

    public ProductsPresenter(){
        Log.i("NULL REF:::::::::::: ", BaseApplication.getsAppComponent() == null ? "get app NULL" : "get app ALL RIGHT");
        BaseApplication.getsAppComponent().inject(this);
    }

    /*@Override
    protected void onFirstViewAttach(){
        super.onFirstViewAttach();
        loadProducts();
    }*/

    public void loadProducts() {
        getProductsFromApi();
    }

    private void getProductsFromApi() {
        getViewState().showRefreshing();
        unsubscribeOnDestroy(mTestAppService.getProducts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<Product[]>()
        {

            @Override
            public void onSuccess(Product[] products) {
                Log.i("GET PRODUCTS METHOD::", "WE ARE IN SUCCESS");
                getViewState().hideRefreshing();
                onLoadingSuccess(products);
            }

            @Override
            public void onError(Throwable e) {
                getViewState().hideRefreshing();
                onLoadingFailed(e);
            }
        }));
    }

    private void onLoadingSuccess(Product[] products) {
        getViewState().addToList(Utils.arrayToList(products));
    }

    private void onLoadingFailed(Throwable throwable){
        getViewState().showError(throwable.getMessage());
    }
}
