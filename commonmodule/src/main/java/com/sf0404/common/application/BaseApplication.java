package com.sf0404.common.application;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.sf0404.common.application.injection.AppInjector;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public abstract class BaseApplication extends Application implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    protected abstract String getAppName();

    @Override
    public void onCreate() {
        super.onCreate();
        initAutoInjection();
    }

    private void initAutoInjection() {
        AppInjector.init(this);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
