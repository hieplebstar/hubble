package com.cinatic.demo2.models.responses;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ErrorResponse {
    @SerializedName("error")
    final private String status;

    @SerializedName("error_description")
    final private String message;
}
