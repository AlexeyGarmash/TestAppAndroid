package com.example.testapp.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.testapp.R;
import com.example.testapp.mvp.models.UserPost;
import com.example.testapp.mvp.presenters.RegisterPresenter;
import com.example.testapp.mvp.views.RegisterView;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends MvpAppCompatActivity implements RegisterView {


    @InjectPresenter
    RegisterPresenter mRegisterPresenter;

    @BindView(R.id.btnRegister)
    Button mButtonRegister;

    @BindView(R.id.editTextUsername_Reg)
    TextInputEditText mInputEditTextUsername;

    @BindView(R.id.editTextPassword_Reg)
    TextInputEditText mInputEditTextPassword;

    @BindView(R.id.editTextConfirm)
    TextInputEditText mInputEditTextConfirmPassword;

    @BindView(R.id.progressBarRegister)
    ProgressBar mProgressBarRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_actvity);
        ButterKnife.bind(this);
        mButtonRegister.setOnClickListener(view -> {
            register();
        });
    }

    private void register(){
        String username = mInputEditTextUsername.getText().toString();
        String password = mInputEditTextPassword.getText().toString();
        String confirmPassword = mInputEditTextConfirmPassword.getText().toString();

        if(username == "" || password == ""){
            showError("Fields empty");
            return;
        }

        if(!password.equals(confirmPassword)){
            showError("Password must be same");
            return;
        }
        mRegisterPresenter.register(new UserPost(username, password));
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
        mProgressBarRegister.setVisibility(View.VISIBLE);
        mButtonRegister.setEnabled(false);
    }

    @Override
    public void hideRefreshing() {
        mProgressBarRegister.setVisibility(View.GONE);
        mButtonRegister.setEnabled(true);
    }
}
