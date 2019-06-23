package com.example.testapp.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.testapp.R;
import com.example.testapp.activities.ProductDetailsActivity;
import com.example.testapp.adapters.OnItemClickListener;
import com.example.testapp.adapters.ProductsAdapter;
import com.example.testapp.base.BaseApplication;
import com.example.testapp.mvp.models.Product;
import com.example.testapp.mvp.presenters.ProductsPresenter;
import com.example.testapp.mvp.views.AllProductsView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragment extends MvpAppCompatFragment implements AllProductsView, OnItemClickListener<Product> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Product> products;

    @BindView(R.id.recyclerProducts)
    RecyclerView mRecyclerViewProducts;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    ProductsAdapter mProductsAdapter;

    @InjectPresenter
    ProductsPresenter mProductsPresenter;

    public ProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        products = new ArrayList<>();
        mRecyclerViewProducts.requestFocus();
        mProductsAdapter = new ProductsAdapter(products, this);

        mRecyclerViewProducts.setAdapter(mProductsAdapter);
    }


    @Override
    public void addToList(ArrayList<Product> products_) {
        clearData();
        products.addAll(products_);
        mProductsAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearData() {
        products.clear();
        mProductsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(BaseApplication.getsAppComponent().getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideError() {

    }

    @Override
    public void onStartLoading() {

    }

    @Override
    public void onFinishLoading() {

    }

    @Override
    public void showRefreshing() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRefreshing() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(Product item) {
        Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
        intent.putExtra(ProductDetailsActivity.PARCELABLE_PRODUCT, item);
        startActivity(intent);
    }
}