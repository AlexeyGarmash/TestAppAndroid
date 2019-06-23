package com.example.testapp.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.testapp.R;
import com.example.testapp.common.Common;
import com.example.testapp.common.Utils;
import com.example.testapp.dialogs.LeaveCommentDialog;
import com.example.testapp.mvp.models.Product;
import com.example.testapp.mvp.presenters.ProductDetailsPresenter;
import com.example.testapp.mvp.views.ProductDetailsView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailsFragment extends MvpAppCompatFragment implements ProductDetailsView {

    private static final String PARAM_PRODUCT = "product";

    @BindView(R.id.productName)
    TextView mTextViewProductName;

    @BindView(R.id.productDescription)
    TextView mTextViewProductDescription;

    @BindView(R.id.productImageDetail)
    ImageView mImageViewProductImage;

    @BindView(R.id.toolbarDetail)
    Toolbar mToolbarProductDetails;

    @BindView(R.id.btnLeaveComment)
    Button mButtonShowDialog;

    private Product mProduct;
    private LeaveCommentDialog mLeaveCommentDialog;


    @InjectPresenter
    ProductDetailsPresenter mProductDetailsPresenter;


    public ProductDetailsFragment() {
        // Required empty public constructor
    }

    public static ProductDetailsFragment newInstance(Product product) {
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(PARAM_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProduct = getArguments().getParcelable(PARAM_PRODUCT);
            if(mProduct != null){
                mProductDetailsPresenter.setmProduct(mProduct);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mButtonShowDialog.setOnClickListener(view1 -> {
            mLeaveCommentDialog = new LeaveCommentDialog(getContext(), R.style.FullHeightDialog, mProduct);
            mLeaveCommentDialog.setmOnButtonSendCommentClickListener(feedback -> {
                mProductDetailsPresenter.sendFeedback(feedback);
            });
            mLeaveCommentDialog.show();
        });
    }

    @Override
    public void showItem(Product product) {
        mTextViewProductDescription.setText(product.getDescription());
        mTextViewProductName.setText(product.getTitle());
        Utils.setImageByURL(
                mImageViewProductImage,
                mImageViewProductImage.getWidth(),
                mImageViewProductImage.getHeight(),
                Common.BASE_IMAGES+product.getImgUrl());
        mToolbarProductDetails.setTitle(product.getTitle());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
