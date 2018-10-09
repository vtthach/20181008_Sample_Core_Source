package com.sf0404.common.rxjava;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;

public class RxJavaHandler {

    public DisposableObserver runOnMainThread(Callback<Boolean> callback) {
        DisposableObserver<Boolean> autoDispose = new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                callback.run(aBoolean);
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
        getMainThreadObservable().subscribe(autoDispose);
        return autoDispose;
    }

    private Observable<Boolean> getMainThreadObservable() {
        return Observable.just(true).observeOn(AndroidSchedulers.mainThread());
    }

    @FunctionalInterface
    public interface Callback<T> {
        void run(T result);
    }
}
