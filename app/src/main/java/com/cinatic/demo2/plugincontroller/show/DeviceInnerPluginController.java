package com.cinatic.demo2.plugincontroller.show;

import com.android.appkit.ActionBarMode;
import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.base.fragment.AnimatingFragmentPluginController;
import com.cinatic.demo2.events.show.ShowDeviceInnerEvent;
import com.cinatic.demo2.fragments.deviceinner.DeviceInnerFragment;
import com.cinatic.demo2.hubble.R;

import org.greenrobot.eventbus.Subscribe;

public class DeviceInnerPluginController extends AnimatingFragmentPluginController<ShowDeviceInnerEvent, DeviceInnerFragment> {

    @Subscribe
    public void onEvent(ShowDeviceInnerEvent event) {
        super.onEvent(event);
    }

    @Override
    protected String createActionBarTitle() {
        return AppApplication.getStringResource(R.string.devices_label);
    }

    @Override
    protected boolean shouldBeAddedToBackStack() {
        return true;
    }

    @Override
    protected ActionBarMode createActionBarMode() {
        return ActionBarMode.OVERLAY_MODE;
    }

    @Override
    protected DeviceInnerFragment createFragment(ShowDeviceInnerEvent event) {
        return DeviceInnerFragment.newInstance();
    }
}
