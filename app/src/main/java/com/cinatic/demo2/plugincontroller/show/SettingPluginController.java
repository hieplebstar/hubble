package com.cinatic.demo2.plugincontroller.show;

import com.android.appkit.ActionBarMode;
import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.base.fragment.AnimatingFragmentPluginController;
import com.cinatic.demo2.events.show.ShowHomeDeviceEvent;
import com.cinatic.demo2.events.show.ShowSettingEvent;
import com.cinatic.demo2.fragments.homedevice.DevicesFragment;
import com.cinatic.demo2.fragments.setting.SettingFragment;
import com.cinatic.demo2.hubble.R;

import org.greenrobot.eventbus.Subscribe;

public class SettingPluginController extends AnimatingFragmentPluginController<ShowSettingEvent, SettingFragment> {

    @Subscribe
    public void onEvent(ShowSettingEvent event) {
        super.onEvent(event);
    }

    @Override
    protected String createActionBarTitle() {
        return AppApplication.getStringResource(R.string.setting_label);
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
    protected SettingFragment createFragment(ShowSettingEvent event) {
        return SettingFragment.newInstance();
    }
}
