package com.cinatic.demo2.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by HiepLe on 4/4/2017.
 */
@Data
public class AuthenticationDTO {
    @SerializedName("os")
    final String os;
    @SerializedName("model")
    final String model;
    @SerializedName("oauth_type")
    final String oauthType;
    @SerializedName("oauth_token")
    final String oauthToken;
    @SerializedName("is_refresh")
    final boolean is_refresh;
    @SerializedName("login")
    final String userName;
    @SerializedName("password")
    final String password;
}
