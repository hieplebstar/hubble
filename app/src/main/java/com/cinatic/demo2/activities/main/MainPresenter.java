package com.cinatic.demo2.activities.main;


import com.android.appkit.presenter.EventListeningPresenter;

import com.cinatic.demo2.events.show.ShowSetupWelcomeEvent;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class MainPresenter extends EventListeningPresenter<MainView>{
    MainPresenter(){
        post(new ShowSetupWelcomeEvent());
    }
}
