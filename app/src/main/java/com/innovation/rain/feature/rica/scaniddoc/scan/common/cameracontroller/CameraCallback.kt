package com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller

import java.io.File

interface CameraCallback {
    fun onSuccess(file: File)

    fun onError(error: String)
}