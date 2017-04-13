package com.cinatic.demo2.models.responses;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ErrorResponse {
    @SerializedName("status")
    final private String status;

    @SerializedName("msg")
    final private String message;
}
