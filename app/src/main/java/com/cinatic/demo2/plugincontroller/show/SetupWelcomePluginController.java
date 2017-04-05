package com.cinatic.demo2.plugincontroller.show;

import com.android.appkit.ActionBarMode;

import org.greenrobot.eventbus.Subscribe;

import com.cinatic.demo2.base.fragment.AnimatingFragmentPluginController;
import com.cinatic.demo2.events.show.ShowSetupWelcomeEvent;
import com.cinatic.demo2.fragments.setup.welcome.WelcomeFragment;

public class SetupWelcomePluginController extends AnimatingFragmentPluginController<ShowSetupWelcomeEvent, WelcomeFragment> {

    @Subscribe
    public void onEvent(ShowSetupWelcomeEvent event) {
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
    protected WelcomeFragment createFragment(ShowSetupWelcomeEvent event) {
        return WelcomeFragment.newInstance();
    }
}
