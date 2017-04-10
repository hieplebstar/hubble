package com.cinatic.demo2.events;

import com.cinatic.demo2.models.DeviceListItem;
import com.cinatic.demo2.models.DeviceReplayListItem;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by HiepLe on 4/4/2017.
 */

@Data
@AllArgsConstructor
public class DeviceReplayListDoReturnEvent {
    final List<DeviceReplayListItem> replayList;
}
