package com.innovation.rain.feature.rica.scaniddoc.scan.idbook.presenter

import com.innovation.rain.feature.rica.scaniddoc.scan.common.base.presenter.BaseScanIdDocPresenterImpl
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraCallback
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraController
import com.innovation.rain.feature.rica.scaniddoc.scan.idbook.view.ScanIdBookView
import timber.log.Timber
import java.io.File
import javax.inject.Inject


class ScanIdBookPresenterImpl @Inject constructor(cameraController: CameraController,
                                                  view: ScanIdBookView)
    : BaseScanIdDocPresenterImpl<ScanIdBookView>(view, cameraController), ScanIdBookPresenter {

    private var capturedfile: File? = null

    override fun getCameraCallback() = object : CameraCallback {
        override fun onSuccess(file: File) {
            capturedfile = file
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
