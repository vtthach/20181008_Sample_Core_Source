package com.innovation.rain.feature.rica.scaniddoc.scan.passport.injection

import com.innovation.rain.feature.rica.scaniddoc.scan.passport.view.ScanPassportFragment
import com.sf0404.core.application.scope.PerView
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class ScanPassportBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = [ScanPassportViewModule::class, ScanPassportControllerModule::class])
    internal abstract fun contributeFragment(): ScanPassportFragment
}