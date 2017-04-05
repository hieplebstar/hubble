package com.cinatic.demo2.plugincontroller.show;

import com.android.appkit.ActionBarMode;
import com.cinatic.demo2.base.fragment.AnimatingFragmentPluginController;
import com.cinatic.demo2.events.show.ShowDeviceActivatedEvent;
import com.cinatic.demo2.fragments.setup.activated.DeviceActivatedFragment;

import org.greenrobot.eventbus.Subscribe;

public class DeviceActivatedPluginController extends AnimatingFragmentPluginController<ShowDeviceActivatedEvent, DeviceActivatedFragment> {

    @Subscribe
    public void onEvent(ShowDeviceActivatedEvent event) {
        super.onEvent(event);
    }

    @Override
    protected boolean shouldBeAddedToBackStack() {
        return false;
    }

    @Override
    protected ActionBarMode createActionBarMode() {
        return ActionBarMode.FULL_MODE;
    }

    @Override
    protected DeviceActivatedFragment createFragment(ShowDeviceActivatedEvent event) {
        return DeviceActivatedFragment.newInstance();
    }
}
