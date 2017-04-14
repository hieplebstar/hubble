package com.cinatic.demo2.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by HiepLe on 4/4/2017.
 */
@Data
public class UpdatePasswordDTO {
    @SerializedName("current_password")
    final String currentPassword;

    @SerializedName("password")
    final String password;

    @SerializedName("confirm_password")
    final String confirmPassword;
}
