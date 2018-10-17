package com.innovation.rain.feature.rica.home.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.rica.sample.SampleFragment

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