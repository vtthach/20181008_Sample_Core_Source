package com.innovation.rain.feature.rica.home.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.agentlogin.view.RicaHomeFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class RicaHomeBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = [RicaHomeViewModule::class])
    internal abstract fun contributeFragment(): RicaHomeFragment
}