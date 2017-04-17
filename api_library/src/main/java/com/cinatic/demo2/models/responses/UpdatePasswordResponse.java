package com.cinatic.demo2.models.responses;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by HiepLe on 2/1/17.
 */

@Data
public class UpdatePasswordResponse {
    @SerializedName("id")
    private String id;

    @SerializedName("email")
    private String email;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("policy_agreed")
    private String policyAgreed;

    @SerializedName("date_created")
    private String dateCreated;

    @SerializedName("last_updated")
    private String lastUpdated;

    @SerializedName("last_sign_in")
    private String lastSignIn;

    @SerializedName("last_sign_in_ip")
    private String lastSignInIp;

    @SerializedName("last_sign_in_count")
    private String lastSignInCount;
}
