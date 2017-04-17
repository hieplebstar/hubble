package com.cinatic.demo2.activities.login;


import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.events.UserDoLoginEvent;
import com.cinatic.demo2.events.UserDoLoginReturnEvent;
import com.cinatic.demo2.events.UserDoResetPasswordEvent;
import com.cinatic.demo2.events.UserDoResetPasswordReturnEvent;
import com.cinatic.demo2.events.show.ShowFeedbackMessageEvent;
import com.cinatic.demo2.hubble.R;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class LoginPresenter extends EventListeningPresenter<LoginView> {

    @Subscribe
    public void onEvent(UserDoLoginReturnEvent event) {
        view.showLoading(false);
        view.directToMainActivity();
    }

    @Subscribe
    public void onEvent(UserDoResetPasswordReturnEvent event) {
        view.showLoading(false);
        view.showSnackBar(AppApplication.getStringResource(R.string.reset_pass_success));
    }

    @Subscribe
    public void onEvent(UserDoResetPasswordEvent event) {
        view.showLoading(true);
    }

    @Subscribe
    public void onShowFeedbackMessageEvent(ShowFeedbackMessageEvent event) {
        view.showLoading(false);
        view.showSnackBar(event.getMessage());
    }

    public void doLogin(String userName, String password) {
        view.showLoading(true);
        post(new UserDoLoginEvent(userName, password, 0, ""));
    }
}
