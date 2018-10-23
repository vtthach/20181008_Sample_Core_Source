package com.innovation.rain.feature.rica.poa.home.injection

import com.innovation.rain.feature.rica.poa.home.view.ProofOfAddressFragment
import com.sf0404.core.application.scope.PerView

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