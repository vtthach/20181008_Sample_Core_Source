package com.innovation.rain.feature.rica.scaniddoc.home.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.rica.scaniddoc.home.view.ScanIdDocFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class ScanIdDocBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = [ScanIdDocViewModule::class])
    internal abstract fun contributeFragment(): ScanIdDocFragment
}