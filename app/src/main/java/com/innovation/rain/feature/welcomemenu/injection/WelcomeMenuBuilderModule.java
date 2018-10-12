package com.innovation.rain.feature.welcomemenu.injection;

import com.sf0404.core.application.scope.PerView;
import com.innovation.rain.feature.welcomemenu.view.WelcomeMenuFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class WelcomeMenuBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = {WelcomeMenuViewModule.class
//            , WelcomeMenuUseCaseModule.class //TODO
    })
    abstract WelcomeMenuFragment contributeFragment();
}