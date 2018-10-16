package com.innovation.rain.feature.collection.scaniddoc.cameracontroller

import java.io.File

interface CameraCallback {
    fun onSuccess(file: File)

    fun onError(error: String)
}