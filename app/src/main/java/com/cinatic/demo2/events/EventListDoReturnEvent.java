package com.cinatic.demo2.events;

import com.cinatic.demo2.models.responses.Device;
import com.cinatic.demo2.models.responses.DeviceEvent;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by HiepLe on 4/4/2017.
 */

@Data
@AllArgsConstructor
public class EventListDoReturnEvent {
    final int totalPages;
    final int totalEvents;
    final List<DeviceEvent> eventList;
}
