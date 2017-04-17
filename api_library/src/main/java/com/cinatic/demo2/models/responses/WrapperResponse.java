package com.cinatic.demo2.models.responses;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by Hiep.Le on 10/10/2016.
 */

@Data
public class WrapperResponse<T> {
    @SerializedName("status")
    final private String status;

    @SerializedName("msg")
    final private String message;

    @SerializedName("data")
    final private T data;
}
