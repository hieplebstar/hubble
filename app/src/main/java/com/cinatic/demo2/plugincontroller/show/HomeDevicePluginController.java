package com.cinatic.demo2.plugincontroller.show;

import com.android.appkit.ActionBarMode;
import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.base.fragment.AnimatingFragmentPluginController;
import com.cinatic.demo2.events.show.ShowHomeDeviceEvent;
import com.cinatic.demo2.fragments.homedevice.DevicesFragment;
import com.cinatic.demo2.hubble.R;

import org.greenrobot.eventbus.Subscribe;

public class HomeDevicePluginController extends AnimatingFragmentPluginController<ShowHomeDeviceEvent, DevicesFragment> {

    @Subscribe
    public void onEvent(ShowHomeDeviceEvent event) {
        super.onEvent(event);
    }

    @Override
    protected String createActionBarTitle() {
        return AppApplication.getStringResource(R.string.devices_label);
    }

    @Override
    protected boolean shouldBeAddedToBackStack() {
        return false;
    }

    @Override
    protected ActionBarMode createActionBarMode() {
        return ActionBarMode.HOME_MODE;
    }

    @Override
    protected DevicesFragment createFragment(ShowHomeDeviceEvent event) {
        return DevicesFragment.newInstance();
    }
}
