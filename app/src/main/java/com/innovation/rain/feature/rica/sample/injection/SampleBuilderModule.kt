package com.innovation.rain.feature.rica.sample.injection

import com.innovation.rain.feature.rica.home.injection.SampleViewModule
import com.innovation.rain.feature.rica.sample.SampleFragment
import com.sf0404.core.application.scope.PerView

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