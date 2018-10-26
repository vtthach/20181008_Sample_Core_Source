package com.sf0404.common.rxjava;


import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaHandler {

    public static DisposableObserver runOnMainThread(Callback<Boolean> callback) {
        DisposableObserver<Boolean> autoDispose = getOnNextCallback(callback);
        getMainThreadObservable(0).subscribe(autoDispose);
        return autoDispose;
    }

    public static DisposableObserver runDelayMainThread(Callback<Boolean> callback, long delay) {
        DisposableObserver<Boolean> autoDispose = getOnNextCallback(callback);
        getMainThreadObservable(delay).subscribe(autoDispose);
        return autoDispose;
    }

    private static <T> DisposableObserver<T> getOnNextCallback(Callback<T> callback) {
        return new DisposableObserver<T>() {
            @Override
            public void onNext(T result) {
                callback.onNext(result);
            }

            @Override
            public void onError(Throwable e) {
                dispose();
            }

            @Override
            public void onComplete() {
                dispose();
            }
        };
    }

    private static Observable<Boolean> getMainThreadObservable(long delay) {
        return Observable.just(true).delay(delay, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> void startApiObservable(@NotNull Observable<T> connect, DisposableObserver<T> disposable) {
        connect.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(disposable);
    }

    public interface Callback<T> {
        void onNext(T result);
    }
}
