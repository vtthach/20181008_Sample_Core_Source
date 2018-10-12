package com.innovation.rain.feature.collection.signin.injection;

import com.sf0404.core.application.scope.PerView;
import com.innovation.rain.feature.collection.signin.view.ClientSignInFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class ClientSignInBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = {ClientSignInViewModule.class
//            , WelcomMenuUseCaseModule.class //TODO
    })
    abstract ClientSignInFragment contributeFragment();
}