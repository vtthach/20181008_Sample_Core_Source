package com.innovation.rain.feature.rica.poa.scan.injection

import com.innovation.rain.feature.rica.poa.scan.presenter.ScanPOAPresenter
import com.innovation.rain.feature.rica.poa.scan.presenter.ScanPOAPresenterImpl
import com.innovation.rain.feature.rica.poa.scan.view.ScanPOAFragment
import com.innovation.rain.feature.rica.poa.scan.view.ScanPOAView
import com.sf0404.core.application.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class ScanPOAViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: ScanPOAFragment): ScanPOAView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: ScanPOAPresenterImpl): ScanPOAPresenter
}
