package com.cinatic.demo2.models.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

/**
 * Created by HiepLe on 2/1/17.
 */

@Data
public class DeviceEvent {
    @SerializedName("id")
    private String id;

    @SerializedName("created_date")
    private String createdDate;

    @SerializedName("event_type")
    private String eventType;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("snapshot")
    private String snapshot;

    @SerializedName("data")
    private List<DeviceEventData> data;

}
