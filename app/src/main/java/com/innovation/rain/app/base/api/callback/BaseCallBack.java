package com.innovation.rain.app.base.api.callback;


public interface BaseCallBack<T> {
    void onStart();

    void onError(Throwable e);

    void onSuccess(T info);

    void onNetworkException(Throwable e);

    void onTechnicalException(Throwable e);

    void onFinish();
}
