package com.innovation.rain.feature.collection.scaniddoc.scanidcard.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.collection.scaniddoc.cameracontroller.CameraController
import com.innovation.rain.feature.collection.scaniddoc.cameracontroller.CameraControllerImpl
import com.innovation.rain.feature.collection.scaniddoc.scanidcard.presenter.ScanIdCardPresenter
import com.innovation.rain.feature.collection.scaniddoc.scanidcard.presenter.ScanIdCardPresenterImpl
import com.innovation.rain.feature.collection.scaniddoc.scanidcard.view.ScanIdCardFragment
import com.innovation.rain.feature.collection.scaniddoc.scanidcard.view.ScanIdCardView
import dagger.Binds
import dagger.Module

@Module
abstract class ScanIdCardViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: ScanIdCardFragment): ScanIdCardView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: ScanIdCardPresenterImpl): ScanIdCardPresenter

    @PerView
    @Binds
    internal abstract fun provideController(controller: CameraControllerImpl): CameraController
}
