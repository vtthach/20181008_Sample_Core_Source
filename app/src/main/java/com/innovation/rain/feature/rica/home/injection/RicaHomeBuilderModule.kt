package com.innovation.rain.feature.rica.home.injection

import com.innovation.rain.feature.rica.home.view.RicaHomeFragment
import com.sf0404.core.application.scope.PerView
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