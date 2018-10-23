package com.innovation.rain.feature.collection.simdispenser.injection;

import com.innovation.rain.feature.collection.signin.view.DispenseFragment;
import com.sf0404.core.application.scope.PerView;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class DispenseBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = {DispenseViewModule.class
            , DispenseUseCaseModule.class
    })
    abstract DispenseFragment contributeFragment();
}