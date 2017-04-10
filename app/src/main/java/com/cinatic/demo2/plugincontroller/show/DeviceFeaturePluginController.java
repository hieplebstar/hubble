package com.cinatic.demo2.plugincontroller.show;

import com.android.appkit.ActionBarMode;
import com.cinatic.demo2.base.fragment.AnimatingFragmentPluginController;
import com.cinatic.demo2.events.show.ShowDeviceFeatureEvent;
import com.cinatic.demo2.fragments.devicefeature.DeviceFeatureFragment;

import org.greenrobot.eventbus.Subscribe;

public class DeviceFeaturePluginController extends AnimatingFragmentPluginController<ShowDeviceFeatureEvent, DeviceFeatureFragment> {

    @Subscribe
    public void onEvent(ShowDeviceFeatureEvent event) {
        super.onEvent(event);
    }

    @Override
    protected boolean shouldBeAddedToBackStack() {
        return true;
    }

    @Override
    protected ActionBarMode createActionBarMode() {
        return ActionBarMode.FULL_MODE;
    }

    @Override
    protected DeviceFeatureFragment createFragment(ShowDeviceFeatureEvent event) {
        return DeviceFeatureFragment.newInstance();
    }
}
