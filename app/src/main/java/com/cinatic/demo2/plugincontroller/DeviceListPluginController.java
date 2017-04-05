package com.cinatic.demo2.plugincontroller;

import com.android.appkit.controller.EventPluginController;
import com.cinatic.demo2.events.DeviceListDoLoadEvent;
import com.cinatic.demo2.events.DeviceListDoReturnEvent;
import com.cinatic.demo2.models.DeviceListItem;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HiepLe on 4/5/2017.
 */

public class DeviceListPluginController extends EventPluginController{
    @Subscribe
    public void onDeviceListDoLoadEvent(DeviceListDoLoadEvent event) {
        post(new DeviceListDoReturnEvent(createMockData()));
    }

    private List<DeviceListItem> createMockData() {
        List<DeviceListItem> data = new ArrayList<DeviceListItem>();
        for (int i =0; i< 20; i ++){
            data.add( new DeviceListItem(""));
        }
        return data;
    }

}
