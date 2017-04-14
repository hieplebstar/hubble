package com.cinatic.demo2.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.base.activity.CalligraphyFontActivity;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.activities.introduction.IntroductionActivity;
import com.cinatic.demo2.activities.main.MainActivity;
import com.cinatic.demo2.activities.register.RegisterActivity;
import com.cinatic.demo2.views.customs.ProgressButton;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class LoginActivity extends CalligraphyFontActivity implements LoginView {

    @BindView(R.id.progressbutton_login_continues)
    ProgressButton mProgressButton;
    @BindView(R.id.username_login)
    EditText mUserNameEditText;
    @BindView(R.id.password_login)
    EditText mPasswordEditText;
    @BindView(R.id.container_login)
    View mContainer;

    private Unbinder mUnbinder;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUnbinder = ButterKnife.bind(this);
        mPresenter = new LoginPresenter();
        mPresenter.start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.stop();
        mUnbinder.unbind();
    }

    @Override
    public void showLoading(boolean isLoading) {
        if (isLoading) {
            mProgressButton.enableLoadingState();
        } else {
            mProgressButton.disableLoadingState();
        }
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(mContainer, message, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(5);
        snackbar.show();
    }

    @Override
    public void directToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void directToIntroductionActivity() {
        showLoading(false);
        Intent intent = new Intent(this, IntroductionActivity.class);
        startActivity(intent);
        finish();
    }

    public void directToRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.progressbutton_login_continues)
    public void onLoginClick() {
        if (validate()){
            mPresenter.doLogin(mUserNameEditText.getText().toString(), mPasswordEditText.getText().toString());
        }
    }

    @OnClick(R.id.create_account_login)
    public void onCreateAccountClick() {
        directToRegisterActivity();
    }


    @OnClick(R.id.forgot_pass_login)
    public void onForgetPassClick() {
        directToRegisterActivity();
    }

    private boolean validate(){
        String userName = mUserNameEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        if (TextUtils.isEmpty(userName)){
            showSnackBar(AppApplication.getStringResource(R.string.warning_username));
            return false;
        }
        if (TextUtils.isEmpty(password)){
            showSnackBar(AppApplication.getStringResource(R.string.warning_password));
            return false;
        }
        return true;
    }
}
