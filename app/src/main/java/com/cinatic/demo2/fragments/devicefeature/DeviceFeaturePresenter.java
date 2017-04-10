package com.cinatic.demo2.fragments.devicefeature;


import com.android.appkit.event.FragmentDoDetachEvent;
import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.events.DeviceReplayListDoLoadEvent;
import com.cinatic.demo2.events.DeviceReplayListDoReturnEvent;
import com.cinatic.demo2.events.DeviceTimelineListDoLoadEvent;
import com.cinatic.demo2.events.DeviceTimelineListDoReturnEvent;
import com.cinatic.demo2.events.show.ShowDeviceFeatureEvent;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class DeviceFeaturePresenter extends EventListeningPresenter<DeviceFeatureView> {

    public void showPreview() {
        post(new FragmentDoDetachEvent(DeviceFeatureFragment.class));
    }
}
