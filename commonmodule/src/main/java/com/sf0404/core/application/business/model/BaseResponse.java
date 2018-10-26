package com.sf0404.core.application.business.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("errorMessage")
    public String message;
    @SerializedName("errorCode")
    public int code;
    @SerializedName("success")
    public boolean success;
}
