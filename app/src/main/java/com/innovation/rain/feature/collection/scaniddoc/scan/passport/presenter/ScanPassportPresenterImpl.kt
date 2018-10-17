package com.innovation.rain.feature.collection.scaniddoc.scan.passport.presenter

import com.innovation.rain.feature.collection.scaniddoc.scan.common.base.BaseScanIdDocPresenterImpl
import com.innovation.rain.feature.collection.scaniddoc.scan.common.cameracontroller.CameraCallback
import com.innovation.rain.feature.collection.scaniddoc.scan.common.cameracontroller.CameraController
import com.innovation.rain.feature.collection.scaniddoc.scan.passport.view.ScanPassportView
import timber.log.Timber
import java.io.File
import javax.inject.Inject


class ScanPassportPresenterImpl @Inject constructor(cameraController: CameraController,
                                                    view: ScanPassportView)
    : BaseScanIdDocPresenterImpl<ScanPassportView>(view, cameraController), ScanPassportPresenter {

    private var file: File? = null

    override fun getCameraCallback() = object : CameraCallback {
        override fun onSuccess(file: File) {
            this@ScanPassportPresenterImpl.file = file
            view.showSuccessScreen()
            Timber.i("Saved image at $file")
        }

        override fun onError(error: String) {
            //TODO: NTL
        }
    }
}
