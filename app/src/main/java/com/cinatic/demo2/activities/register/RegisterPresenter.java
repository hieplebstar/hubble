package com.cinatic.demo2.activities.register;

import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.UserDoRegisterEvent;
import com.cinatic.demo2.events.UserDoRegisterReturnedEvent;
import com.cinatic.demo2.events.show.ShowFeedbackMessageEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HiepLe on 21/2/2017.
 */
public class RegisterPresenter extends EventListeningPresenter<RegisterView>{

    @Subscribe
    public void onEvent(UserDoRegisterReturnedEvent event) {
        view.showLoading(false);
        if(event.getRegisterResponse() == null) return;
        view.directToLoginActivity();
    }

    @Subscribe
    public void onShowFeedbackMessageEvent(ShowFeedbackMessageEvent event) {
        view.showLoading(false);
        view.showSnackBar(event.getMessage());
    }

    public void register(String userName, String email, String password, String confirmPassword) {
        view.showLoading(true);
        post(new UserDoRegisterEvent(userName, email, password, confirmPassword));
    }
}
