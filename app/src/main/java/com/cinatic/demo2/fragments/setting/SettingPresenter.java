package com.cinatic.demo2.fragments.setting;


import android.content.Intent;

import com.android.appkit.presenter.EventListeningPresenter;
import com.cinatic.demo2.AppApplication;
import com.cinatic.demo2.activities.login.LoginActivity;
import com.cinatic.demo2.events.DeviceListDoLoadEvent;
import com.cinatic.demo2.events.DeviceListDoReturnEvent;
import com.cinatic.demo2.events.UserDoChangePasswordEvent;
import com.cinatic.demo2.events.UserDoChangePasswordReturnEvent;
import com.cinatic.demo2.events.UserDoLoadInfoReturnEvent;
import com.cinatic.demo2.events.show.ShowDeviceInnerEvent;
import com.cinatic.demo2.fragments.homedevice.DevicesView;
import com.cinatic.demo2.hubble.R;
import com.cinatic.demo2.persistances.SettingPreferences;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by HiepLe on 7/6/2016.
 */
public class SettingPresenter extends EventListeningPresenter<SettingView> {
    @Subscribe(sticky = true)
    public void onUserDoLoadInfoReturnEvent(UserDoLoadInfoReturnEvent event) {
        view.updateView(event.getUserInfo());
    }

    @Subscribe
    public void onEvent(UserDoChangePasswordReturnEvent event) {
        view.showLoading(false);
        view.showSnackBar(AppApplication.getStringResource(R.string.change_pass_success));
    }

    @Subscribe
    public void onEvent(UserDoChangePasswordEvent event) {
        view.showLoading(true);
    }

    public void doLogout() {
        new SettingPreferences().putRefreshToken("");
    }
}
