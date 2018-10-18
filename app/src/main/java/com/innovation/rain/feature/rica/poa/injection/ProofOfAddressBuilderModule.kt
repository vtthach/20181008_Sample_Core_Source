package com.innovation.rain.feature.rica.poa.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.rica.poa.view.ProofOfAddressFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class ProofOfAddressBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = arrayOf(ProofOfAddressViewModule::class))
    internal abstract fun contributeFragment(): ProofOfAddressFragment
}