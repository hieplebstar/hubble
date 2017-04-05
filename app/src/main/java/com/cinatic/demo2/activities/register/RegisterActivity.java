package com.cinatic.demo2.activities.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.activities.introduction.IntroductionActivity;
import com.cinatic.demo2.activities.login.LoginActivity;
import com.cinatic.demo2.views.customs.ProgressButton;

/**
 * Created by HiepLe on 21/2/2017.
 */
public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @BindView(R.id.progressbutton_register_next)
    ProgressButton mProgressButton;
    @BindView(R.id.textview_register_agree)
    TextView mAgreeTextView;

    private Unbinder mUnbinder;
    private RegisterPresenter mPresenter;;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUnbinder = ButterKnife.bind(this);
        mPresenter = new RegisterPresenter();
        initView();
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
    public void directToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }



    public void directToIntroductionActivity() {
        Intent intent = new Intent(this, IntroductionActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.progressbutton_register_login)
    public void onLoginNowClick() {
        directToLoginActivity();
    }

    @OnClick(R.id.progressbutton_register_next)
    public void onRegisterClick() {
        directToIntroductionActivity();
    }

    public void initView() {
        String agree = AppApplication.getStringResource(R.string.agree_label);
        String privacy = AppApplication.getStringResource(R.string.privacy_policy_label);
        String agreeAnnounce = AppApplication.getStringResource(R.string.agree_announce, agree, privacy);
        int agreePosition = agreeAnnounce.indexOf(agree);
        int privatePosition = agreeAnnounce.indexOf(privacy);
        SpannableString spannable = new SpannableString(agreeAnnounce);
        spannable.setSpan(new ForegroundColorSpan(AppApplication.getIntColor(R.color.orange)), agreePosition, agreePosition + agree.length(), 0);
        spannable.setSpan(new ForegroundColorSpan(AppApplication.getIntColor(R.color.orange)), privatePosition, privatePosition + privacy.length(), 0);
        mAgreeTextView.setText(spannable);
    }
}
