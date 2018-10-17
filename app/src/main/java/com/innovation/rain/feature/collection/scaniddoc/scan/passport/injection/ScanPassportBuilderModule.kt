package com.innovation.rain.feature.collection.scaniddoc.scan.passport.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.collection.scaniddoc.scan.passport.view.ScanPassportFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class ScanPassportBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = [ScanPassportViewModule::class])
    internal abstract fun contributeFragment(): ScanPassportFragment
}