package com.innovation.rain.feature.rica.scaniddoc.scan.idbook.injection

import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraController
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraControllerImpl
import com.innovation.rain.feature.rica.scaniddoc.scan.idbook.presenter.ScanIdBookPresenter
import com.innovation.rain.feature.rica.scaniddoc.scan.idbook.presenter.ScanIdBookPresenterImpl
import com.innovation.rain.feature.rica.scaniddoc.scan.idbook.view.ScanIdBookFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.idbook.view.ScanIdBookView
import com.sf0404.core.application.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class ScanIdBookViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: ScanIdBookFragment): ScanIdBookView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: ScanIdBookPresenterImpl): ScanIdBookPresenter

    @PerView
    @Binds
    internal abstract fun provideController(controller: CameraControllerImpl): CameraController
}
