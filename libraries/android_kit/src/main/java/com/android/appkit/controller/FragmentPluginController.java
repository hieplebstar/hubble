package com.android.appkit.controller;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.v4.app.Fragment;

import com.android.appkit.ActionBarMode;
import com.android.appkit.event.FragmentDoAttachEvent;
import com.android.appkit.presenter.EventPostingPresenter;

import org.greenrobot.eventbus.Subscribe;

public abstract class FragmentPluginController<ShowFragmentEvent, FragmentType extends Fragment> extends EventPostingPresenter {

    protected abstract FragmentType createFragment(ShowFragmentEvent event);
    protected abstract boolean shouldBeAddedToBackStack();

    public void onEvent(ShowFragmentEvent event) {
        attachFragment(event);
    }

    private void attachFragment(ShowFragmentEvent event) {
        FragmentType fragment = createFragment(event);
        if (fragment.getArguments() == null) {
            fragment.setArguments(new Bundle());
        }
        fragment.getArguments().putSerializable(ActionBarMode.BUNDLE_ID, createActionBarMode());
        FragmentDoAttachEvent attachFragmentEvent = new FragmentDoAttachEvent(fragment, shouldBeAddedToBackStack(), createActionBarTitle());
        attachFragmentEvent.setAnimations(createEnterAnimationResId(), createExitAnimationResId(), createPopEnterAnimationResId(), createPopExitAnimationResId());
        post(attachFragmentEvent);
    }

    protected ActionBarMode createActionBarMode() {
        return ActionBarMode.HOME_MODE;
    }

    protected String createActionBarTitle() {
        return null;
    }

    protected @AnimRes
    int createEnterAnimationResId() {
        return 0;
    }

    protected @AnimRes
    int createExitAnimationResId() {
        return 0;
    }

    protected @AnimRes
    int createPopEnterAnimationResId() {
        return 0;
    }

    protected @AnimRes
    int createPopExitAnimationResId() {
        return 0;
    }
}
