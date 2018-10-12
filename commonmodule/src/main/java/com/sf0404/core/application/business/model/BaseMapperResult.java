package com.sf0404.core.application.business.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseMapperResult implements Serializable {
    @SerializedName("api_code")
    private String apiCode; // Use to check generic exception (ie: invalid token)

    public void setApiCode(String code) {
        apiCode = code;
    }

    public String getApiCode() {
        return apiCode;
    }
}
