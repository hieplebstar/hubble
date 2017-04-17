package com.cinatic.demo2.plugincontroller;

import com.android.appkit.controller.EventPluginController;
import com.cinatic.demo2.events.DeviceListDoLoadEvent;
import com.cinatic.demo2.events.DeviceListDoReturnEvent;
import com.cinatic.demo2.events.UserDoLoginReturnEvent;
import com.cinatic.demo2.events.show.ShowFeedbackMessageEvent;
import com.cinatic.demo2.manager.UserDeviceManager;
import com.cinatic.demo2.models.responses.Device;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by HiepLe on 4/5/2017.
 */

public class UserDevicePluginController extends EventPluginController {

    private UserDeviceManager mUserDeviceManager;

    public UserDevicePluginController() {
        mUserDeviceManager = new UserDeviceManager();
    }

    UserDeviceManager.OnGetListUserDeviceListener onGetListUserDeviceListener = new UserDeviceManager.OnGetListUserDeviceListener() {
        @Override
        public void onFailure(Throwable error) {
            handleError(error);
        }

        @Override
        public void onSuccess(List<Device> result) {
            post(new DeviceListDoReturnEvent(result));
        }
    };

    @Subscribe
    public void onEvent(DeviceListDoLoadEvent event) {
        mUserDeviceManager.getListUserDevices(onGetListUserDeviceListener);
    }

    private void handleError(Throwable throwable) {
        post(new ShowFeedbackMessageEvent(throwable.getMessage()));
    }
}
