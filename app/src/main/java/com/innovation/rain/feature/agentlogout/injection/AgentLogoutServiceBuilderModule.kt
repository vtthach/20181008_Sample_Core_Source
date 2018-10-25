package com.innovation.rain.feature.agentlogout.injection

import com.innovation.rain.feature.agentlogout.AgentLogoutIntentService
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class AgentLogoutServiceBuilderModule {

    @ContributesAndroidInjector(modules = [AgentLogoutRepoModule::class])
    abstract fun contributeService(): AgentLogoutIntentService
}