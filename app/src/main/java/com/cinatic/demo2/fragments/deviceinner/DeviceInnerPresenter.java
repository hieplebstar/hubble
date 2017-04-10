package com.cinatic.demo2.fragments.deviceinner;


import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.DeviceListDoLoadEvent;
import com.cinatic.demo2.events.DeviceListDoReturnEvent;
import com.cinatic.demo2.events.DeviceReplayListDoLoadEvent;
import com.cinatic.demo2.events.DeviceReplayListDoReturnEvent;
import com.cinatic.demo2.events.DeviceTimelineListDoLoadEvent;
import com.cinatic.demo2.events.DeviceTimelineListDoReturnEvent;
import com.cinatic.demo2.events.show.ShowDeviceFeatureEvent;
import com.cinatic.demo2.fragments.homedevice.DevicesView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class DeviceInnerPresenter extends EventListeningPresenter<DeviceInnerView> {

    @Subscribe
    public void onDeviceReplayListDoReturnEvent(DeviceReplayListDoReturnEvent event){
        view.showReplayList(event.getReplayList());
    }

    @Subscribe
    public void onDeviceTimelineListDoReturnEvent(DeviceTimelineListDoReturnEvent event){
        view.showTimelineList(event.getTimelineList());
    }

    public void loadReplayList(){
        post(new DeviceReplayListDoLoadEvent());
    }

    public void loadTimelineList(){
        post(new DeviceTimelineListDoLoadEvent());
    }

    public void showFullScreen(){
        post(new ShowDeviceFeatureEvent());
    }
}
