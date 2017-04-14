package com.cinatic.demo2.fragments.setting;


import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.DeviceListDoLoadEvent;
import com.cinatic.demo2.events.DeviceListDoReturnEvent;
import com.cinatic.demo2.events.UserDoLoadInfoReturnEvent;
import com.cinatic.demo2.events.show.ShowDeviceInnerEvent;
import com.cinatic.demo2.fragments.homedevice.DevicesView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class SettingPresenter extends EventListeningPresenter<SettingView> {
    @Subscribe(sticky = true)
    public void onUserDoLoadInfoReturnEvent(UserDoLoadInfoReturnEvent event) {
        view.updateView(event.getUserInfo());
    }
}
