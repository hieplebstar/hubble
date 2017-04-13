package com.cinatic.demo2.activities.login;


import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.UserDoLoginEvent;
import com.cinatic.demo2.events.UserDoLoginReturnEvent;
import com.cinatic.demo2.events.show.ShowFeedbackMessageEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class LoginPresenter extends EventListeningPresenter<LoginView> {

    @Subscribe
    public void onUserDoLoginEvent(UserDoLoginReturnEvent event) {
        view.showLoading(false);
        view.directToIntroductionActivity();
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
