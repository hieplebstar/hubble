package com.cinatic.demo2.plugincontroller.show;

import com.android.appkit.ActionBarMode;
import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.base.fragment.AnimatingFragmentPluginController;
import com.cinatic.demo2.events.show.ShowHomeDeviceEvent;
import com.cinatic.demo2.events.show.ShowHomeEventEvent;
import com.cinatic.demo2.fragments.homedevice.DevicesFragment;
import com.cinatic.demo2.fragments.homeevent.EventFragment;
import com.cinatic.demo2.hubble.R;

import org.greenrobot.eventbus.Subscribe;

public class HomeEventPluginController extends AnimatingFragmentPluginController<ShowHomeEventEvent, EventFragment> {

    @Subscribe
    public void onEvent(ShowHomeEventEvent event) {
        super.onEvent(event);
    }

    @Override
    protected String createActionBarTitle() {
        return AppApplication.getStringResource(R.string.events_label);
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
    protected EventFragment createFragment(ShowHomeEventEvent event) {
        return EventFragment.newInstance();
    }
}
