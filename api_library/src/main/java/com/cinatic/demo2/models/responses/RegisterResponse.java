package com.cinatic.demo2.models.responses;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by HiepLe on 2/1/17.
 */

@Data
public class RegisterResponse {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("refresh_token")
    private String refreshToken;

    @SerializedName("user_name")
    private String user_name;
}
