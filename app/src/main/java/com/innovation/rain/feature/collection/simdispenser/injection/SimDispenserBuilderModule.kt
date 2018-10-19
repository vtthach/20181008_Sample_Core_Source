package com.innovation.rain.feature.collection.simdispenser.injection

import com.sf0404.core.application.scope.PerView

import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class SimDispenserBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = arrayOf(SimDispenserViewModule::class))
    internal abstract fun contributeFragment(): SimDispenserFragment
}