package com.innovation.rain.feature.rica.scaniddoc.scan.passport.injection

import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraController
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraControllerImpl
import com.innovation.rain.feature.rica.scaniddoc.scan.passport.presenter.ScanPassportPresenter
import com.innovation.rain.feature.rica.scaniddoc.scan.passport.presenter.ScanPassportPresenterImpl
import com.innovation.rain.feature.rica.scaniddoc.scan.passport.view.ScanPassportFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.passport.view.ScanPassportView
import com.sf0404.core.application.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class ScanPassportViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: ScanPassportFragment): ScanPassportView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: ScanPassportPresenterImpl): ScanPassportPresenter

    @PerView
    @Binds
    internal abstract fun provideController(controller: CameraControllerImpl): CameraController
}
