package com.example.testapp.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.testapp.R;
import com.example.testapp.adapters.CommentsAdapter;
import com.example.testapp.mvp.models.Comment;
import com.example.testapp.mvp.models.Product;
import com.example.testapp.mvp.presenters.CommentsPresenter;
import com.example.testapp.mvp.views.CommentsView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentsFragment extends MvpAppCompatFragment implements CommentsView {

    private static final String PARAM_PRODUCT = "product";

    private Product mProduct;

    @InjectPresenter
    CommentsPresenter mCommentsPresenter;


    @BindView(R.id.recyclerComments)
    RecyclerView mRecyclerViewComments;

    @BindView(R.id.progressBarComments)
    ProgressBar mProgressBarComments;

    private CommentsAdapter mCommentsAdapter;

    private ArrayList<Comment> mComments;

    public CommentsFragment() {
        // Required empty public constructor
    }


    public static CommentsFragment newInstance(Product product) {
        CommentsFragment fragment = new CommentsFragment();
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
                mCommentsPresenter.setmProduct(mProduct);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comments, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mComments = new ArrayList<>();
        mCommentsAdapter = new CommentsAdapter(mComments);
        mRecyclerViewComments.setAdapter(mCommentsAdapter);
        mRecyclerViewComments.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
    }

    @Override
    public void showComments(ArrayList<Comment> comments) {
        clearComments();
        mComments.addAll(comments);
        mCommentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearComments() {
        mComments.clear();
        mCommentsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideError() {
        //TODO:
    }

    @Override
    public void onStartLoading() {
        //TODO:
    }

    @Override
    public void onFinishLoading() {
        //TODO:
    }

    @Override
    public void showRefreshing() {
        mProgressBarComments.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRefreshing() {
        mProgressBarComments.setVisibility(View.GONE);
    }
}
