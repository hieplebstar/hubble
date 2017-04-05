package com.cinatic.demo2.fragments.setup.activated;


import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.show.ShowBottomTabEvent;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class DeviceActivatedPresenter extends EventListeningPresenter {
    void directToHome() {
        post(new ShowBottomTabEvent());
    }
}
