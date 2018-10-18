package com.sf0404.core.application.business.usecase;

import android.support.annotation.NonNull;

import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

public class BaseSubscriber<T> extends DisposableObserver<T> {

    private final UseCaseCallback<T> useCaseCallback;

    public BaseSubscriber(@NonNull UseCaseCallback<T> useCaseCallback) {
        this.useCaseCallback = useCaseCallback;
    }

    @Override
    protected void onStart() {
        super.onStart();
        useCaseCallback.onStart();
    }

    @Override
    public void onNext(T t) {
        useCaseCallback.onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        Timber.e(e, "BaseSubscriber - onError: " + e.getMessage());
        useCaseCallback.onError(e);
        useCaseCallback.onFinish();
    }

    @Override
    public void onComplete() {
        useCaseCallback.onFinish();
    }

    public interface UseCaseCallback<T> {
        void onSuccess(T t);

        void onError(Throwable e);

        void onStart();

        void onFinish();
    }
}
