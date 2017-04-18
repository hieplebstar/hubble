package com.cinatic.demo2.plugincontroller.show;

import com.android.appkit.ActionBarMode;
import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.base.fragment.AnimatingFragmentPluginController;
import com.cinatic.demo2.events.show.ShowDeviceFeatureEvent;
import com.cinatic.demo2.events.show.ShowVideoPlayEvent;
import com.cinatic.demo2.fragments.devicefeature.DeviceFeatureFragment;
import com.cinatic.demo2.fragments.video.VideoPlayFragment;
import com.cinatic.demo2.hubble.R;

import org.greenrobot.eventbus.Subscribe;

public class VideoPlayPluginController extends AnimatingFragmentPluginController<ShowVideoPlayEvent, VideoPlayFragment> {

    @Subscribe
    public void onEvent(ShowVideoPlayEvent event) {
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
        return ActionBarMode.FULL_MODE;
    }

    @Override
    protected VideoPlayFragment createFragment(ShowVideoPlayEvent event) {
        return VideoPlayFragment.newInstance(event.getUrl());
    }

    @Override
    protected int createEnterAnimationResId() {
        return 0;
    }

    @Override
    protected int createExitAnimationResId() {
        return 0;
    }

    @Override
    protected int createPopEnterAnimationResId() {
        return 0;
    }

    @Override
    protected int createPopExitAnimationResId() {
        return 0;
    }
}
