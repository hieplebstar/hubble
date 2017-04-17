package com.cinatic.demo2.models.responses;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by HiepLe on 2/1/17.
 */

@Data
public class Subscription {
    @SerializedName("plan_id")
    private String planId;

    @SerializedName("plan_changed_date")
    private String planChangedDate;

    @SerializedName("plan_expiry_date")
    private String planExpiryDate;

    @SerializedName("trial_used")
    private boolean trialUsed;

    @SerializedName("trialNumber")
    private int trial_number;
}
