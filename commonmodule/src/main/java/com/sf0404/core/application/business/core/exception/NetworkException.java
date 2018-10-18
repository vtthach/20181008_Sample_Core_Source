package com.sf0404.core.application.business.core.exception;

public class NetworkException extends BaseException {
    private boolean mIsInternetConnectionError = false;

    public boolean isInternetConnectionError() {
        return mIsInternetConnectionError;
    }

    public void setInternetConnectionError(boolean internetConnectionError) {
        mIsInternetConnectionError = internetConnectionError;
    }
}