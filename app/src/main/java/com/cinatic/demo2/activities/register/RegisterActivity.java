package com.cinatic.demo2.activities.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
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
import com.cinatic.demo2.activities.login.LoginActivity;
import com.cinatic.demo2.utils.StringUtils;
import com.cinatic.demo2.views.customs.ProgressButton;

/**
 * Created by HiepLe on 21/2/2017.
 */
public class RegisterActivity extends CalligraphyFontActivity implements RegisterView {

    @BindView(R.id.progressbutton_register_next)
    ProgressButton mProgressButton;
    @BindView(R.id.textview_register_agree)
    TextView mAgreeTextView;
    @BindView(R.id.username_register)
    EditText mUserNameEditText;
    @BindView(R.id.email_register)
    EditText mEmailEditText;
    @BindView(R.id.password_register)
    EditText mPasswordEditText;
    @BindView(R.id.confirm_password_register)
    EditText mConfirmEditText;
    @BindView(R.id.container_register)
    View mContainer;

    private Unbinder mUnbinder;
    private RegisterPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUnbinder = ButterKnife.bind(this);
        mPresenter = new RegisterPresenter();
        mPresenter.start(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mPresenter.stop();
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

    @OnClick(R.id.progressbutton_register_login)
    public void onLoginNowClick() {
        directToLoginActivity();
    }

    @OnClick(R.id.progressbutton_register_next)
    public void onRegisterClick() {
        if (validate()) {
            mPresenter.register(mUserNameEditText.getText().toString(),
                    mEmailEditText.getText().toString(),
                    mPasswordEditText.getText().toString(),
                    mConfirmEditText.getText().toString());
        }
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

    private boolean validate() {
        String userName = mUserNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();
        String passwordConfirm = mConfirmEditText.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            showSnackBar(AppApplication.getStringResource(R.string.warning_username));
            return false;

        }
        if (!StringUtils.validateEmail(email)) {
            showSnackBar(AppApplication.getStringResource(R.string.warning_email));
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            showSnackBar(AppApplication.getStringResource(R.string.warning_password));
            return false;
        }
        if (!passwordConfirm.equals(password)) {
            showSnackBar(AppApplication.getStringResource(R.string.warning_confirm_password));
            return false;
        }
        return true;
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(mContainer, message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setMaxLines(5);
        snackbar.show();
    }
}
