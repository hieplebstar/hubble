package com.cinatic.demo2.activities.main;


import com.android.appkit.presenter.EventListeningPresenter;

import com.cinatic.demo2.events.UserDoLoadInfoEvent;
import com.cinatic.demo2.events.UserDoLoadInfoReturnEvent;
import com.cinatic.demo2.events.show.ShowBottomTabEvent;
import com.cinatic.demo2.events.show.ShowFeedbackMessageEvent;
import com.cinatic.demo2.events.show.ShowSetupWelcomeEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class MainPresenter extends EventListeningPresenter<MainView>{

    @Override
    public void start(MainView mainView) {
        super.start(mainView);
        post(new UserDoLoadInfoEvent());
    }

    @Subscribe
    public void onUserDoLoginEvent(UserDoLoadInfoReturnEvent event) {
//        post(new ShowSetupWelcomeEvent());
        post(new ShowBottomTabEvent());
    }

    @Subscribe
    public void onShowFeedbackMessageEvent(ShowFeedbackMessageEvent event) {
        view.showLoading(false);
        view.showSnackBar(event.getMessage());
    }
}
