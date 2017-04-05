package com.cinatic.demo2.fragments.setup.welcome;


import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.show.ShowDeviceActivatedEvent;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class WelcomePresenter extends EventListeningPresenter {
    void showSetupResult(){
        post(new ShowDeviceActivatedEvent());
    }
}
