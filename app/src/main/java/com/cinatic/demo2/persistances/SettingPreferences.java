package com.cinatic.demo2.persistances;


import com.android.appkit.persistences.BasePreferences;
import com.cinatic.demo2.AppApplication;

/**
 * Created by HiepLe on 18/10/2016.
 */
public class SettingPreferences extends BasePreferences {
    public static final String PREF_FIRST_RUN = APPLICATION_PREFERENCES + "_FIRST_RUN";

    public SettingPreferences() {
        super(AppApplication.getInstance());
    }

    @Override
    public void clear() {
        mPreferences.edit().remove(PREF_FIRST_RUN).apply();
    }

    public boolean isFirstRun() {
        return mPreferences.getBoolean(PREF_FIRST_RUN, true);
    }

    public void putFirstRun(boolean firstRun) {
        mPreferences.edit().putBoolean(PREF_FIRST_RUN, firstRun).apply();
    }
}
