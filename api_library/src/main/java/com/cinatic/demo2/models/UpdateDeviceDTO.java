package com.cinatic.demo2.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by HiepLe on 4/4/2017.
 */
@Data
public class UpdateDeviceDTO {
    @SerializedName("name")
    final String name;

    @SerializedName("version")
    final String version;

    @SerializedName("time_zone")
    final String timeZone;

    @SerializedName("local_ip")
    final String localIp;

    @SerializedName("router_ssid")
    final String routerSSID;

    @SerializedName("router_ip")
    final String routerIp;
}
