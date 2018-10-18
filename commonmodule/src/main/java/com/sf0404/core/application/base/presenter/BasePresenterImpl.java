package com.sf0404.core.application.base.presenter;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.View;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenterImpl<T> implements BasePresenter {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected  T view;

    public BasePresenterImpl(T view) {
        this.view = view;
    }

    @Override
    @CallSuper
    public void onCreate(Bundle savedInstanceState) {
        // Stub method to override
    }

    @Override
    @CallSuper
    public void onCreateView(View parentView) {
        // Stub method to override
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        // Stub method to override
    }

    @Override
    @CallSuper
    public void onDestroy() {
        // Stub method to override
        compositeDisposable.dispose();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Stub method to override
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Stub method to override
    }

    @Override
    @CallSuper
    public void onViewCreated(Bundle savedInstanceState, Bundle arguments) {
        // Stub method to override
    }

    @Override
    @CallSuper
    public void onResume() {
        // Stub method to override
    }

    @Override
    @CallSuper
    public void onPause() {
        // Stub method to override
    }

    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    protected String getDebugErrorMessage(@NonNull Throwable e) {
        return e.getMessage();
    }
}
