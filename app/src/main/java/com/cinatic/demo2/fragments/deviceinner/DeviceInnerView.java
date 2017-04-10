package com.cinatic.demo2.fragments.deviceinner;

import com.cinatic.demo2.models.DeviceReplayListItem;
import com.cinatic.demo2.models.DeviceTimelineListItem;

import java.util.List;

/**
 * Created by HiepLe on 7/6/2016.
 */
public interface DeviceInnerView {
    void showReplayList(List<DeviceReplayListItem> replayList);
    void showTimelineList(List<DeviceTimelineListItem> timelineList);
}
