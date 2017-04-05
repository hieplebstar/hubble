package com.android.appkit.event;

import android.support.v4.app.Fragment;

public class FragmentDoDetachEvent {

    public Class<? extends Fragment> fragmentClass;

    public FragmentDoDetachEvent(Class<? extends Fragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
    }
}
