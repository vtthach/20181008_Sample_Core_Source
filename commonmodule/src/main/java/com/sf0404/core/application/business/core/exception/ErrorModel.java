package com.sf0404.core.application.business.core.exception;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ErrorModel implements Serializable {
    @SerializedName("errorCode")
    public int code;
    @SerializedName("errorMessage")
    public String message;
}
