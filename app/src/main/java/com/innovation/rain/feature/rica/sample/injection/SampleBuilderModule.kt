package com.innovation.rain.feature.rica.dashboard.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.agentlogin.view.DashboardFragment
import com.innovation.rain.feature.agentlogin.view.SampleFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class SampleBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = [SampleViewModule::class])
    internal abstract fun contributeFragment(): SampleFragment
}