package com.sf0404.core.application.business.core.exception;

import okhttp3.Headers;

public abstract class BaseException extends RuntimeException {

    protected String mCode;
    private String mMessage;
    private int mResponseConstant;
    private Headers mHeaders;

    private int mHttpCode = 200;

    public int getResponseConstant() {
        return mResponseConstant;
    }

    public void setResponseConstant(int mResponseConstant) {
        this.mResponseConstant = mResponseConstant;
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String mCode) {
        this.mCode = mCode;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public void setHttpCode(int httpCode) {
        this.mHttpCode = httpCode;
    }

    public int getHttpCode() {
        return mHttpCode;
    }

    public Headers getHeaders() {
        return mHeaders;
    }

    public void setHeaders(Headers headers) {
        mHeaders = headers;
    }
}
