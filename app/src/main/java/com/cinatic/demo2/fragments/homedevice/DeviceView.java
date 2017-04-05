package com.cinatic.demo2.fragments.homedevice;

import com.cinatic.demo2.models.DeviceListItem;

import java.util.List;

/**
 * Created by HiepLe on 7/6/2016.
 */
public interface DeviceView {
    void showDeviceList(List<DeviceListItem> deviceList);
}
