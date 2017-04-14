package com.cinatic.demo2.fragments.homedevice;


import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.DeviceListDoLoadEvent;
import com.cinatic.demo2.events.DeviceListDoReturnEvent;
import com.cinatic.demo2.events.show.ShowDeviceActivatedEvent;
import com.cinatic.demo2.events.show.ShowDeviceInnerEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class DevicesPresenter extends EventListeningPresenter<DevicesView> {

    @Subscribe
    public void onDeviceListDoReturnEvent(DeviceListDoReturnEvent event){
        view.showDeviceList(event.getDeviceList());
    }

    public void loadDeviceList(){
        post(new DeviceListDoLoadEvent());
    }

    public void showDetail(){
        post(new ShowDeviceInnerEvent());
    }

    void showSetupResult(){
        post(new ShowDeviceActivatedEvent());
    }
}
