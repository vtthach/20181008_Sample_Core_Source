package com.innovation.rain.feature.agentlogin.injection

import com.innovation.rain.feature.agentlogin.view.AgentLoginFragment
import com.sf0404.core.application.scope.PerView
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class AgentLoginBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = [AgentLoginViewModule::class, AgentLoginUseCaseModule::class])
    abstract fun contributeFragment(): AgentLoginFragment
}