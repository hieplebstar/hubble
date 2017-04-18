package com.cinatic.demo2.plugincontroller;

import com.android.appkit.controller.EventPluginController;
import com.cinatic.demo2.events.DeviceListDoLoadEvent;
import com.cinatic.demo2.events.DeviceListDoReturnEvent;
import com.cinatic.demo2.events.EventListDoLoadEvent;
import com.cinatic.demo2.events.EventListDoReturnEvent;
import com.cinatic.demo2.events.show.ShowFeedbackMessageEvent;
import com.cinatic.demo2.manager.DeviceEventManager;
import com.cinatic.demo2.manager.UserDeviceManager;
import com.cinatic.demo2.models.responses.Device;
import com.cinatic.demo2.models.responses.DeviceEvent;
import com.cinatic.demo2.models.responses.DeviceEventResponse;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by HiepLe on 4/5/2017.
 */

public class DeviceEventPluginController extends EventPluginController {

    private DeviceEventManager mManager;

    public DeviceEventPluginController() {
        mManager = new DeviceEventManager();
    }

    DeviceEventManager.OnGetListDeviceEventListener onGetListDeviceEventListener = new DeviceEventManager.OnGetListDeviceEventListener() {
        @Override
        public void onFailure(Throwable error) {
            handleError(error);
        }

        @Override
        public void onSuccess(DeviceEventResponse result) {
            post(new EventListDoReturnEvent(result.getTotalPages(), result.getTotalPages(), result.getEventList()));
        }
    };

    @Subscribe
    public void onEvent(EventListDoLoadEvent event) {
        mManager.getListDeviceEvent(event.getDeviceIds(), event.getPage(), onGetListDeviceEventListener);
    }

    private void handleError(Throwable throwable) {
        post(new ShowFeedbackMessageEvent(throwable.getMessage()));
    }
}
