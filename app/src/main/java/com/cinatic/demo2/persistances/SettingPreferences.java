package com.cinatic.demo2.persistances;


import com.android.appkit.persistences.BasePreferences;
import com.cinatic.demo2.AppApplication;

/**
 * Created by HiepLe on 18/10/2016.
 */
public class SettingPreferences extends BasePreferences {
    public static final String PREF_FIRST_RUN = APPLICATION_PREFERENCES + "_FIRST_RUN";
    public static final String PREF_REFRESH_TOKEN = APPLICATION_PREFERENCES + "_REFRESH_TOKEN";

    public SettingPreferences() {
        super(AppApplication.getInstance());
    }

    @Override
    public void clear() {
        mPreferences.edit().remove(PREF_FIRST_RUN).apply();
        mPreferences.edit().remove(PREF_REFRESH_TOKEN).apply();
    }

    public String getRefreshToken() {
        return mPreferences.getString(PREF_REFRESH_TOKEN, "");
    }

    public void putRefreshToken(String refreshToken) {
        mPreferences.edit().putString(PREF_REFRESH_TOKEN, refreshToken).apply();
    }

    public boolean isFirstRun() {
        return mPreferences.getBoolean(PREF_FIRST_RUN, true);
    }

    public void putFirstRun(boolean firstRun) {
        mPreferences.edit().putBoolean(PREF_FIRST_RUN, firstRun).apply();
    }
}
