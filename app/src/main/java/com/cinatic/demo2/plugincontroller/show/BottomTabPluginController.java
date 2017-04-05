package com.cinatic.demo2.plugincontroller.show;

import com.android.appkit.ActionBarMode;
import com.cinatic.demo2.base.fragment.AnimatingFragmentPluginController;
import com.cinatic.demo2.events.show.ShowBottomTabEvent;
import com.cinatic.demo2.fragments.bottomtab.BottomTabFragment;

import org.greenrobot.eventbus.Subscribe;

public class BottomTabPluginController extends AnimatingFragmentPluginController<ShowBottomTabEvent, BottomTabFragment> {

    @Subscribe
    public void onEvent(ShowBottomTabEvent event) {
        super.onEvent(event);
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
    protected BottomTabFragment createFragment(ShowBottomTabEvent event) {
        return BottomTabFragment.newInstance();
    }
}
