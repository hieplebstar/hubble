package com.cinatic.demo2.fragments.setting;

import com.cinatic.demo2.models.DeviceListItem;
import com.cinatic.demo2.models.responses.UserInfoResponse;

import java.util.List;

/**
 * Created by HiepLe on 7/6/2016.
 */
public interface SettingView {
    void updateView(UserInfoResponse userInfoResponse);
}
