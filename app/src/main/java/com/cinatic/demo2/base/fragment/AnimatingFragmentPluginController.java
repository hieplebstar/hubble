package com.cinatic.demo2.base.fragment;


import android.support.v4.app.Fragment;

import com.android.appkit.controller.FragmentPluginController;

import com.cinatic.demo2.hubble.R;

public abstract class AnimatingFragmentPluginController<ShowFragmentEvent, FragmentType extends Fragment> extends FragmentPluginController<ShowFragmentEvent, FragmentType> {

    @Override
    protected int createEnterAnimationResId() {
        if (shouldBeAddedToBackStack()) {
            return R.anim.slide_in_right;
        }
        return R.anim.abc_fade_in;
    }

    @Override
    protected int createExitAnimationResId() {
        if (shouldBeAddedToBackStack()) {
            return R.anim.slide_out_left;
        }
        return R.anim.abc_fade_out;
    }

    @Override
    protected int createPopEnterAnimationResId() {
        return 0;
    }

    @Override
    protected int createPopExitAnimationResId() {
        if (shouldBeAddedToBackStack()) {
            return R.anim.slide_out_left;
        }
        return 0;
    }
}
