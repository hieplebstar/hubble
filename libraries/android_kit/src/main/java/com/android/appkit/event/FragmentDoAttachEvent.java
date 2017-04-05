package com.android.appkit.event;


import android.support.annotation.AnimRes;
import android.support.v4.app.Fragment;

public class FragmentDoAttachEvent {
    public Fragment fragment;
    public boolean addToBackStack;
    public String actionBarTitle;
    public  int enterAnimation;
    public int exitAnimation;
    public int popEnterAnimation;
    public int popExitAnimation;

    public FragmentDoAttachEvent(Fragment fragment, boolean addToBackStack, String actionBarTitle) {
        this.fragment = fragment;
        this.addToBackStack = addToBackStack;
        this.actionBarTitle = actionBarTitle;
    }

    public void setAnimations(@AnimRes int enter, @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {
        enterAnimation = enter;
        exitAnimation = exit;
        popEnterAnimation = popEnter;
        popExitAnimation = popExit;
    }
}
