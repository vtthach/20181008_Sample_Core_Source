package com.innovation.rain.feature.collection.scaniddoc.scanidbook.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.collection.scaniddoc.cameracontroller.CameraController
import com.innovation.rain.feature.collection.scaniddoc.cameracontroller.CameraControllerImpl
import com.innovation.rain.feature.collection.scaniddoc.scanidbook.presenter.ScanIdBookPresenter
import com.innovation.rain.feature.collection.scaniddoc.scanidbook.presenter.ScanIdBookPresenterImpl
import com.innovation.rain.feature.collection.scaniddoc.scanidbook.view.ScanIdBookFragment
import com.innovation.rain.feature.collection.scaniddoc.scanidbook.view.ScanIdBookView
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
