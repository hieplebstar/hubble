package com.cinatic.demo2.fragments.homedevice;


import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.DeviceListDoLoadEvent;
import com.cinatic.demo2.events.DeviceListDoReturnEvent;
import com.cinatic.demo2.events.show.ShowBottomTabEvent;
import com.cinatic.demo2.events.show.ShowDeviceActivatedEvent;
import com.cinatic.demo2.events.show.ShowDeviceInnerEvent;
import com.cinatic.demo2.events.show.ShowSetupWelcomeEvent;
import com.cinatic.demo2.models.DeviceListItem;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class DevicesPresenter extends EventListeningPresenter<DevicesView> {

    @Subscribe
    public void onEvent(DeviceListDoReturnEvent event) {
        view.showLoading(false);
        if(event.getDeviceList() == null || event.getDeviceList().isEmpty()){
            post(new ShowSetupWelcomeEvent());
        } else {
            view.showDeviceList(event.getDeviceList());
        }
    }

    public void loadDeviceList(){
        view.showLoading(true);
        post(new DeviceListDoLoadEvent());
    }

    public void showDetail(String id, String name, String deviceId){
        post(new ShowDeviceInnerEvent(id, name, deviceId));
    }

    void showSetupResult(){
        post(new ShowDeviceActivatedEvent());
    }
}
