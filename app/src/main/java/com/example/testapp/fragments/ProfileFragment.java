package com.example.testapp.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.testapp.R;
import com.example.testapp.activities.LoginActivity;
import com.example.testapp.activities.MainActivity;
import com.example.testapp.activities.RegisterActivity;
import com.example.testapp.common.Common;
import com.example.testapp.common.Utils;
import com.example.testapp.mvp.presenters.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.internal.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    @BindView(R.id.btnGoLogin)
    Button mButtonGoLogin;

    @BindView(R.id.btnGoRegister)
    Button mButtonGoRegister;

    @BindView(R.id.textStubUsername)
    TextView mTextViewSignInfo;

    @BindView(R.id.textAsUsername)
    TextView mTextViewAsUsername;

    @BindView(R.id.btnLogout)
    Button mButtonLogout;

    @BindView(R.id.btnSetTokenNull)
    Button mButtonSetNullToken;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        uiBehavior();
        mButtonGoLogin.setOnClickListener(view1 -> {
            goLoginActivity();
        });

        mButtonGoRegister.setOnClickListener(view1 -> {
            goRegisterActivity();
        });

        mButtonLogout.setOnClickListener(view1 -> {
            logOut();
        });

        mButtonSetNullToken.setOnClickListener(view1 ->{
            Utils.saveUserDataToPrefs(Common.KEY_TOKEN, null);
        });
    }

    private void logOut() {
        Utils.saveUserDataToPrefs(Common.KEY_USERNAME, null);
        Utils.saveUserDataToPrefs(Common.KEY_PASSWORD, null);
        Utils.saveUserDataToPrefs(Common.KEY_TOKEN, null);
        uiBehavior();
    }

    private void goRegisterActivity() {
        Intent intent = new Intent(getContext(), RegisterActivity.class);
        startActivity(intent);
    }

    private void goLoginActivity() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void uiBehavior(){
        String userName = Utils.getUserData(Common.KEY_USERNAME);
        if(userName == null){
            setProfileUi("You are not signed in!", "");
            mButtonLogout.setVisibility(View.GONE);
        }
        else{
            setProfileUi("You are signed in as: ", userName);
            mButtonLogout.setVisibility(View.VISIBLE);
        }
    }

    private void setProfileUi(String signInfo, String username){
        mTextViewSignInfo.setText(signInfo);
        mTextViewAsUsername.setText(username);
    }
}
