package com.cinatic.demo2.models.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by HiepLe on 2/1/17.
 */

@Data
public class DeviceEventResponse {
    @SerializedName("total_events")
    private int totalEvents;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("events")
    private List<DeviceEvent> eventList;

}
