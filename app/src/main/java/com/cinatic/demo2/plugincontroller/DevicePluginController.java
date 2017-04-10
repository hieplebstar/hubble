package com.cinatic.demo2.plugincontroller;

import com.android.appkit.controller.EventPluginController;
import com.cinatic.demo2.events.DeviceReplayListDoLoadEvent;
import com.cinatic.demo2.events.DeviceReplayListDoReturnEvent;
import com.cinatic.demo2.events.DeviceTimelineListDoLoadEvent;
import com.cinatic.demo2.events.DeviceTimelineListDoReturnEvent;
import com.cinatic.demo2.models.DeviceReplayListItem;
import com.cinatic.demo2.models.DeviceTimelineListItem;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HiepLe on 4/5/2017.
 */

public class DevicePluginController extends EventPluginController{
    @Subscribe
    public void onDeviceTimelineListDoLoadEvent(DeviceTimelineListDoLoadEvent event) {
        post(new DeviceTimelineListDoReturnEvent(createMockTimelineData()));
    }

    @Subscribe
    public void onDeviceReplayListDoLoadEvent(DeviceReplayListDoLoadEvent event) {
        post(new DeviceReplayListDoReturnEvent(createMockReplayData()));
    }

    private List<DeviceTimelineListItem> createMockTimelineData() {
        List<DeviceTimelineListItem> data = new ArrayList<>();
        for (int i =0; i< 20; i ++){
            data.add( new DeviceTimelineListItem(""));
        }
        return data;
    }

    private List<DeviceReplayListItem> createMockReplayData() {
        List<DeviceReplayListItem> data = new ArrayList<>();
        for (int i =0; i< 20; i ++){
            data.add( new DeviceReplayListItem(""));
        }
        return data;
    }

}
