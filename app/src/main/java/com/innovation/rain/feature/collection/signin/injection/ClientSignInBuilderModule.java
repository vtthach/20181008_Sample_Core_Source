package com.innovation.rain.feature.collection.signin.injection;

import com.innovation.rain.feature.collection.signin.view.ClientSignInFragment;
import com.sf0404.core.application.scope.PerView;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class ClientSignInBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = {ClientSignInViewModule.class
            , ClientSignInUseCaseModule.class
    })
    abstract ClientSignInFragment contributeFragment();
}