package com.cinatic.demo2.activities.splash;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cinatic.demo2.activities.introduction.IntroductionActivity;
import com.cinatic.demo2.activities.main.MainActivity;
import com.cinatic.demo2.fragments.setting.SettingPresenter;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.activities.login.LoginActivity;
import com.cinatic.demo2.persistances.SettingPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SplashActivity extends AppCompatActivity implements SplashView{

    @BindView(R.id.splash_container)
    View mContainer;
    @BindView(R.id.progressbar_splash)
    ProgressBar mProgressBar;

    private SplashPresenter mPresenter;
    private SettingPreferences mPreferences;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mUnbinder = ButterKnife.bind(this);
        mPresenter = new SplashPresenter();
        mPresenter.start(this);
        mPreferences = new SettingPreferences();
        if(mPreferences.isAutoLogin() && !TextUtils.isEmpty(mPreferences.getRefreshToken())){
            mPresenter.doLogin(mPreferences.getRefreshToken());
            return;
        }
        directToLoginActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.stop();
        mUnbinder.unbind();
    }

    @Override
    public void directToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void directToLoginActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SettingPreferences preferences = new SettingPreferences();
                if(preferences.isFirstRun()){
                    preferences.putFirstRun(false);
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, IntroductionActivity.class));
                } else {
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();
            }
        }, 1000);
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
    public void showLoading(boolean isLoading) {
        if (isLoading) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

}
