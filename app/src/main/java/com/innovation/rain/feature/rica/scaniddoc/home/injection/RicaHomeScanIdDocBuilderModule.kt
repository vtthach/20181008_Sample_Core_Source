package com.innovation.rain.feature.rica.scaniddoc.home.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.rica.scaniddoc.home.view.RicaHomeScanIdDocFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class RicaHomeScanIdDocBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = [RicaHomeScanIdDocViewModule::class])
    internal abstract fun contributeFragment(): RicaHomeScanIdDocFragment
}