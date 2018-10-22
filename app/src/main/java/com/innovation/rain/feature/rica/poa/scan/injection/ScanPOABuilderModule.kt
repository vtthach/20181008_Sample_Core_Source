package com.innovation.rain.feature.rica.poa.scan.injection

import com.innovation.rain.feature.rica.poa.scan.view.ScanPOAFragment
import com.sf0404.core.application.scope.PerView
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class ScanPOABuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = [ScanPOAViewModule::class, ScanPOAControllerModule::class])
    abstract fun contributeFragment(): ScanPOAFragment
}