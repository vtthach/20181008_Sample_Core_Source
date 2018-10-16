package com.innovation.rain.feature.collection.scaniddoc.cameracontroller

import com.innovation.rain.app.widget.AutoFitTextureView

interface CameraController {
    fun onResume()

    fun onPause()

    fun capture()

    fun setTextureView(autoFitTextureView: AutoFitTextureView)

    fun setCameraCallback(cameraCallback: CameraCallback?)
}