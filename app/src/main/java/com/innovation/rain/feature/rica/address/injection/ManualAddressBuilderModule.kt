package com.innovation.rain.feature.rica.address.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.rica.address.view.ManualAddressFragment

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