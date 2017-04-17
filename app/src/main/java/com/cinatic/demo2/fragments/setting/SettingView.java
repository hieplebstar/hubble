package com.cinatic.demo2.fragments.setting;

import com.cinatic.demo2.models.responses.UserInfo;

/**
 * Created by HiepLe on 7/6/2016.
 */
public interface SettingView {
    void updateView(UserInfo userInfoResponse);
    void showLoading(boolean isLoading);
    void showSnackBar(String message);
}
