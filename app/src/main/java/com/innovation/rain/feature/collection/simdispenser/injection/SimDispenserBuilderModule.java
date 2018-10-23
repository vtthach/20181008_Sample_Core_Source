package com.innovation.rain.feature.collection.simdispenser.injection;

import com.innovation.rain.feature.collection.signin.view.SimDispenserFragment;
import com.sf0404.core.application.scope.PerView;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class SimDispenserBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = {SimDispenserViewModule.class
            , SimDispenserUseCaseModule.class
    })
    abstract SimDispenserFragment contributeFragment();
}