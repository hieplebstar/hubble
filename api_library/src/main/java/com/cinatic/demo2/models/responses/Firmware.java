package com.cinatic.demo2.models.responses;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by HiepLe on 2/1/17.
 */

@Data
public class Firmware {
    @SerializedName("version")
    private String id;

    @SerializedName("status")
    private String status;

    @SerializedName("last_update")
    private String lastUpdate;
}
