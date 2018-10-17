package com.innovation.rain.feature.rica.scaniddoc.scan.idcard.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraController
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraControllerImpl
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.presenter.ScanIdCardPresenter
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.presenter.ScanIdCardPresenterImpl
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.view.ScanIdCardFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.view.ScanIdCardView
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
