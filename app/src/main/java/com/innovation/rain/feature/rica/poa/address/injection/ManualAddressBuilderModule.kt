package com.innovation.rain.feature.rica.poa.address.injection

import com.innovation.rain.feature.rica.poa.address.view.ManualAddressFragment
import com.sf0404.core.application.scope.PerView

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class ManualAddressBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = arrayOf(ManualAddressViewModule::class))
    internal abstract fun contributeFragment(): ManualAddressFragment
}