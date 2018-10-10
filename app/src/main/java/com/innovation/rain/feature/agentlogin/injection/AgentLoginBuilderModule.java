package com.innovation.rain.feature.agentlogin.injection;

import com.innovation.rain.app.injection.scope.PerView;
import com.innovation.rain.feature.agentlogin.view.AgentLoginFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class AgentLoginBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = {AgentLoginViewModule.class
//            , AgentLoginUseCaseModule.class //TODO
    })
    abstract AgentLoginFragment contributeChangePasswordFragment();
}