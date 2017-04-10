package com.cinatic.demo2.activities.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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

    private Unbinder mUnbinder;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUnbinder = ButterKnife.bind(this);
        mPresenter = new LoginPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
    public void directToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void directToIntroductionActivity() {
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
        directToIntroductionActivity();
    }


    @OnClick(R.id.forgot_pass_login)
    public void onForgetPassClick() {
        directToRegisterActivity();
    }
}
