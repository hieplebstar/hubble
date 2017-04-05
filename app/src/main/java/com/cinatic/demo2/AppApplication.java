package com.cinatic.demo2;

import com.android.appkit.AndroidApplication;

/**
 * Created by HiepLe on 4/4/2017.
 */

public class AppApplication extends AndroidApplication {
    @Override
    protected com.android.appkit.eventbus.EventBusController createEventBusController() {
        return new MainEventBusController();
    }
}
