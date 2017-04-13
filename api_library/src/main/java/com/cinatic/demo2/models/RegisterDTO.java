package com.cinatic.demo2.models;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by HiepLe on 4/4/2017.
 */
@Data
public class RegisterDTO {
    @SerializedName("user_name")
    final String userName;

    @SerializedName("email")
    final String email;

    @SerializedName("password")
    final String password;

    @SerializedName("confirm_password")
    final String confirm_password;

    @SerializedName("secure_code")
    final String secure_code;
}
