package com.innovation.rain.feature.agentlogin.injection;

import com.sf0404.core.application.scope.PerView;
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
    abstract AgentLoginFragment contributeFragment();
}