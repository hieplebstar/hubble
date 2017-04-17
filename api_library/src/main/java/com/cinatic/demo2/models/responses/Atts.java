package com.cinatic.demo2.models.responses;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

/**
 * Created by HiepLe on 2/1/17.
 */

@Data
public class Atts {
    @SerializedName("mtst")
    private String mtst;

    @SerializedName("mtss")
    private int mtss;

    @SerializedName("vst")
    private int vst;

    @SerializedName("vth")
    private int vth;

    @SerializedName("htst")
    private int htst;

    @SerializedName("ltst")
    private int ltst;

    @SerializedName("htth")
    private int htth;

    @SerializedName("ltth")
    private int ltth;

    @SerializedName("mvr")
    private int mvr;

    @SerializedName("mcs")
    private int mcs;

    @SerializedName("stm")
    private int stm;

    @SerializedName("strm")
    private int strm;
}
