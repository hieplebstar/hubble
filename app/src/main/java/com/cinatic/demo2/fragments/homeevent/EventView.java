package com.cinatic.demo2.fragments.homeevent;

import com.cinatic.demo2.models.responses.DeviceEvent;

import java.util.List;

/**
 * Created by HiepLe on 7/6/2016.
 */
public interface EventView {
    void showEventList(List<DeviceEvent> eventList);
    void showLoading(boolean isLoading);
    void showSnackBar(String message);
}
