package com.cinatic.demo2.fragments.homedevice;


import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.DeviceListDoLoadEvent;
import com.cinatic.demo2.events.DeviceListDoReturnEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class DevicePresenter extends EventListeningPresenter<DeviceView> {

    @Subscribe
    public void onDeviceListDoReturnEvent(DeviceListDoReturnEvent event){
        view.showDeviceList(event.getDeviceList());
    }

    public void loadDeviceList(){
        post(new DeviceListDoLoadEvent());
    }

    public void showDetail(){
        post(new DeviceListDoLoadEvent());
    }
}
