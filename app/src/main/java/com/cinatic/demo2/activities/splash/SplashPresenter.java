package com.cinatic.demo2.activities.splash;


import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.activities.login.LoginView;
import com.cinatic.demo2.events.UserDoLoginEvent;
import com.cinatic.demo2.events.UserDoLoginReturnEvent;
import com.cinatic.demo2.events.UserDoRefreshTokenEvent;
import com.cinatic.demo2.events.UserDoRefreshTokenReturnEvent;
import com.cinatic.demo2.events.UserDoResetPasswordEvent;
import com.cinatic.demo2.events.UserDoResetPasswordReturnEvent;
import com.cinatic.demo2.events.show.ShowFeedbackMessageEvent;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.persistances.SettingPreferences;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class SplashPresenter extends EventListeningPresenter<SplashView> {

    @Subscribe
    public void onEvent(UserDoRefreshTokenReturnEvent event) {
        view.showLoading(false);
        view.directToMainActivity();
    }

    @Subscribe
    public void onShowFeedbackMessageEvent(ShowFeedbackMessageEvent event) {
        view.showLoading(false);
        view.showSnackBar(event.getMessage());
        view.directToLoginActivity();
    }

    public void doLogin(String refreshToken) {
        view.showLoading(true);
        post(new UserDoRefreshTokenEvent(refreshToken));
    }
}
