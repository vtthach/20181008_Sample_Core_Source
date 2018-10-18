package com.innovation.rain.feature.rica.scaniddoc.home.injection

import com.innovation.rain.feature.rica.scaniddoc.home.view.RicaHomeScanIdDocFragment
import com.sf0404.core.application.scope.PerView
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