package com.sf0404.core.application.business.callback;

public interface BaseCallbackView {
    void toggleProgress(boolean isShow);

    void notifyNetworkError(String msg);

    void notifyTechnicalException(Throwable e);

    void notifyError(String msg);
}
