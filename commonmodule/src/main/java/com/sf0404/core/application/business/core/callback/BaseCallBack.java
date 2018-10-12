package com.sf0404.core.application.business.core.callback;


public interface BaseCallBack<T> {
    void onStart();

    void onError(Throwable e);

    void onSuccess(T info);

    void onNetworkException(Throwable e);

    void onTechnicalException(Throwable e);

    void onFinish();
}
