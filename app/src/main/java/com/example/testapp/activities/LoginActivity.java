package com.example.testapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.testapp.R;
import com.example.testapp.base.BaseApplication;
import com.example.testapp.mvp.models.UserPost;
import com.example.testapp.mvp.presenters.LoginPresenter;
import com.example.testapp.mvp.views.LoginView;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @InjectPresenter
    LoginPresenter mLoginPresenter;

    @BindView(R.id.btnLogIn)
    Button mButtonLogIn;

    @BindView(R.id.editTextUsername)
    EditText mInputEditTextUsername;

    @BindView(R.id.editTextPassword)
    EditText mInputEditTextPassword;

    @BindView(R.id.progressBarLogin)
    ProgressBar mProgressBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mButtonLogIn.setOnClickListener(view -> {
          runLogin();
        });
    }

    private void runLogin() {
        String usernameInput = mInputEditTextUsername.getText().toString();
        String passwordInput = mInputEditTextPassword.getText().toString();

        if(usernameInput == "" || passwordInput == ""){//TODO: replace with Input layout err handler
            showError("Empty field not required");
        }
        else{
            mLoginPresenter.logIn(new UserPost(usernameInput, passwordInput));
        }
    }

    @Override
    public void goToMainActivity() {
        Intent myIntent = new Intent(
                this,
                MainActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(myIntent);
        finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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
        mProgressBarLogin.setVisibility(View.VISIBLE);
        mButtonLogIn.setEnabled(false);
    }

    @Override
    public void hideRefreshing() {
        mProgressBarLogin.setVisibility(View.GONE);
        mButtonLogIn.setEnabled(true);
    }

}
