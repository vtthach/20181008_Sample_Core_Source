package com.innovation.rain.feature.rica.scaniddoc.scan.idbook.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.rica.scaniddoc.scan.idbook.view.ScanIdBookFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class ScanIdBookBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = [ScanIdBookViewModule::class])
    internal abstract fun contributeFragment(): ScanIdBookFragment
}