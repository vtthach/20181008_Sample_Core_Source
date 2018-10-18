package com.innovation.rain.feature.rica.scaniddoc.scan.common.base.presenter

import android.os.Bundle
import com.innovation.rain.app.base.presenter.BasePresenterImpl
import com.innovation.rain.app.widget.AutoFitTextureView
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraCallback
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraController

abstract class BaseScanIdDocPresenterImpl<T>(view: T,
                                             private val cameraController: CameraController)
    : BasePresenterImpl<T>(view), BaseScanIdDocPresenter {

    override fun onResume() {
        super.onResume()
        cameraController.onResume()
    }

    override fun onPause() {
        super.onPause()
        cameraController.onPause()
    }

    override fun capture() {
        cameraController.capture()
    }

    override fun setTextureView(autoFitTextureView: AutoFitTextureView) {
        cameraController.setTextureView(autoFitTextureView)
    }

    override fun onViewCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        super.onViewCreated(savedInstanceState, arguments)
        cameraController.setCameraCallback(getCameraCallback())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraController.setCameraCallback(null)
    }

    abstract fun getCameraCallback(): CameraCallback?
}