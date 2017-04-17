package com.cinatic.demo2.models.responses;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by HiepLe on 2/1/17.
 */

@Data
public class Device {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("mac")
    private String mac;

    @SerializedName("time_zone")
    private String timeZone;

    @SerializedName("is_online")
    private boolean isOnline;

    @SerializedName("last_snapshot")
    private String lastSnapshot;

    @SerializedName("last_snaptshot_time")
    private String lastSnaptshotTime;

    @SerializedName("device_model_id")
    private String deviceModelId;

    @SerializedName("local_ip")
    private String localIp;

    @SerializedName("remote_ip")
    private String remoteIp;

    @SerializedName("firmware")
    private Firmware firmware;

    @SerializedName("subscription")
    private Subscription subscription;

    @SerializedName("last_access_date")
    private String lastAccessDate;

    @SerializedName("created_date")
    private String created_date;

    @SerializedName("updated_date")
    private String updated_date;

    @SerializedName("router_ssid")
    private String router_ssid;

    @SerializedName("router_name")
    private String router_name;

    @SerializedName("settings")
    private String[] settings;

    @SerializedName("atts")
    private Atts atts;
}
