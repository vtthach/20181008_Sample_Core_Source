package com.innovation.rain.feature.rica.agentverification.injection

import com.innovation.rain.feature.rica.agentverification.view.AgentVerificationFragment
import com.sf0404.core.application.scope.PerView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AgentVerificationBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = arrayOf(AgentVerificationViewModule::class))
    internal abstract fun contributeFragment(): AgentVerificationFragment
}