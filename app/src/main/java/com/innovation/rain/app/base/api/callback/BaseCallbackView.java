package com.innovation.rain.app.base.api.callback;

public interface BaseCallbackView {
    void toggleProgress(boolean isShow);

    void notifyNetworkError(String msg);

    void notifyTechnicalException(Throwable e);

    void notifyError(String msg);
}
