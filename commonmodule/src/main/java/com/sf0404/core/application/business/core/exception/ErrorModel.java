package com.sf0404.core.application.business.core.exception;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ErrorModel implements Serializable {
    @SerializedName("code")
    public String code;
    @SerializedName("message")
    public String message;
}
