package com.sf0404.core.application.business.core.callback;

/**
 * @param <V>
 * @param <T> should extends BaseMapperResult
 */
public abstract class BaseCallbackImpl<V extends BaseCallbackView, T> implements BaseCallBack<T> {

    private final V view;

    public BaseCallbackImpl(V view) {
        this.view = view;
    }

    @Override
    public void onStart() {
        view.toggleProgress(true);
    }

    @Override
    public void onFinish() {
        view.toggleProgress(false);
    }

    @Override
    public void onError(Throwable e) {
        view.notifyError(e.getMessage());
    }

    @Override
    public void onTechnicalException(Throwable e) {
        view.notifyTechnicalException(e);
    }

    @Override
    public void onNetworkException(Throwable e) {
        view.notifyNetworkError(e.getMessage());
    }
}
