package com.innovation.rain.feature.collection.scaniddoc.scanpassport.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.collection.scaniddoc.cameracontroller.CameraController
import com.innovation.rain.feature.collection.scaniddoc.cameracontroller.CameraControllerImpl
import com.innovation.rain.feature.collection.scaniddoc.scanpassport.presenter.ScanPassportPresenter
import com.innovation.rain.feature.collection.scaniddoc.scanpassport.presenter.ScanPassportPresenterImpl
import com.innovation.rain.feature.collection.scaniddoc.scanpassport.view.ScanPassportFragment
import com.innovation.rain.feature.collection.scaniddoc.scanpassport.view.ScanPassportView
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
