package com.cinatic.demo2.models.responses;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by HiepLe on 2/1/17.
 */

@Data
public class DeviceEventData {
    @SerializedName("id")
    private String id;

    @SerializedName("file")
    private String file;

    @SerializedName("file_type")
    private int fileType;

    @SerializedName("created_date")
    private String createdDate;
}
