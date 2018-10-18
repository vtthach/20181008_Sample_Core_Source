package com.sf0404.core.application.business.model;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("message")
    public String message;
    @SerializedName("code")
    public String code;

    protected String getResultCode() {
        return code;
    }
}
