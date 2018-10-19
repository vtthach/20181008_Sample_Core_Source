package com.innovation.rain.feature.rica.scaniddoc.scan.idcard.presenter

import com.innovation.rain.feature.rica.scaniddoc.scan.common.base.presenter.BaseScanIdDocPresenterImpl
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraCallback
import com.innovation.rain.feature.rica.scaniddoc.scan.common.cameracontroller.CameraController
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.view.ScanIdCardView
import timber.log.Timber
import java.io.File
import javax.inject.Inject


class ScanIdCardPresenterImpl @Inject constructor(cameraController: CameraController,
                                                  view: ScanIdCardView)
    : BaseScanIdDocPresenterImpl<ScanIdCardView>(view, cameraController), ScanIdCardPresenter {

    private var isScanFirstTime = true

    private var capturedFile: File? = null

    private var firstFile: File? = null

    private var secondsFile: File? = null

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
        if (isScanFirstTime) {
            firstFile = capturedFile
            isScanFirstTime = false
            view.showFlipCardMessage()
            view.showTextureView(true)
        } else {
            secondsFile = capturedFile
            isScanFirstTime = true

            //TODO: call api. Then...
            view.backToHomeRica()
        }
    }
}
