package com.innovation.rain.app.injection.module;

import android.content.Context;

import com.innovation.rain.app.injection.module.model.AppBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context application;

    public AppModule(Context application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    AppBus provideAppBus() {
        return new AppBus();
    }
}
