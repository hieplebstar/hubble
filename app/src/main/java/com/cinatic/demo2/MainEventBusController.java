package com.cinatic.demo2;


import com.android.appkit.eventbus.EventBusController;

import java.util.Arrays;
import java.util.List;

import com.cinatic.demo2.plugincontroller.DeviceEventPluginController;
import com.cinatic.demo2.plugincontroller.DevicePluginController;
import com.cinatic.demo2.plugincontroller.UserDevicePluginController;
import com.cinatic.demo2.plugincontroller.UserPluginController;
import com.cinatic.demo2.plugincontroller.show.BottomTabPluginController;
import com.cinatic.demo2.plugincontroller.show.DeviceActivatedPluginController;
import com.cinatic.demo2.plugincontroller.show.DeviceFeaturePluginController;
import com.cinatic.demo2.plugincontroller.show.DeviceInnerPluginController;
import com.cinatic.demo2.plugincontroller.show.HomeDevicePluginController;
import com.cinatic.demo2.plugincontroller.show.HomeEventPluginController;
import com.cinatic.demo2.plugincontroller.show.SettingPluginController;
import com.cinatic.demo2.plugincontroller.show.SetupWelcomePluginController;
import com.cinatic.demo2.plugincontroller.show.VideoPlayPluginController;

/**
 * Created by HiepLe on 4/4/2017.
 */

public class MainEventBusController extends EventBusController {
    @Override
    protected List<Object> createDefaultSubscribers() {
        return Arrays.asList(new Object[]{
                new BottomTabPluginController(),
                new DeviceInnerPluginController(),
                new DeviceFeaturePluginController(),
                new HomeDevicePluginController(),
                new HomeEventPluginController(),
                new VideoPlayPluginController(),
                new SettingPluginController(),
                new SetupWelcomePluginController(),
                new DeviceActivatedPluginController(),
                new DevicePluginController(),
                new UserPluginController(),
                new UserDevicePluginController(),
                new DeviceEventPluginController()
        });
    }
}
