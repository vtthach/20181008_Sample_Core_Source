package com.innovation.rain.feature.rica.scaniddoc.scan.idcard.injection

import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.view.ScanIdCardFragment
import com.sf0404.core.application.scope.PerView
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class ScanIdCardBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = [ScanIdCardViewModule::class])
    internal abstract fun contributeFragment(): ScanIdCardFragment
}