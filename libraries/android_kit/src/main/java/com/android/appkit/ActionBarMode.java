package com.android.appkit;

import java.io.Serializable;

public enum ActionBarMode implements Serializable {

    HOME_MODE, OVERLAY_MODE, FULL_MODE ;

    public static final String BUNDLE_ID = "extra_actionbar_mode";
}
