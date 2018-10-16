package com.innovation.rain.feature.agentdeclaration.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.agentdeclaration.view.AgentDeclarationFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class AgentDeclarationBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = arrayOf(AgentDeclarationViewModule::class))
    internal abstract fun contributeFragment(): AgentDeclarationFragment
}