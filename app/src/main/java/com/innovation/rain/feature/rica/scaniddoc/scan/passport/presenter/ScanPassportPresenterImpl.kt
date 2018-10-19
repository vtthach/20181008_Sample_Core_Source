package com.innovation.rain.feature.rica.scaniddoc.scan.passport.presenter

import com.innovation.rain.feature.rica.scaniddoc.scan.common.base.presenter.BaseScanIdDocPresenterImpl
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraCallback
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraController
import com.innovation.rain.feature.rica.scaniddoc.scan.passport.view.ScanPassportView
import timber.log.Timber
import java.io.File
import javax.inject.Inject


class ScanPassportPresenterImpl @Inject constructor(cameraController: CameraController,
                                                    view: ScanPassportView)
    : BaseScanIdDocPresenterImpl<ScanPassportView>(view, cameraController), ScanPassportPresenter {

    private var capturedFile: File? = null

    override fun getCameraCallback() = object : CameraCallback {
        override fun onSuccess(file: File) {
            capturedFile = file
            view.reviewImage(file)
            Timber.i("Saved image at $file")
        }

        override fun onError(error: String) {
            //TODO: NTL
        }
    }

    override fun uploadImage() {
        //TODO: call api. Then...
        view.backToHomeRica()
    }
}
