package com.innovation.rain.feature.collection.scaniddoc.scan.idcard.presenter

import com.innovation.rain.feature.collection.scaniddoc.scan.common.base.BaseScanIdDocPresenterImpl
import com.innovation.rain.feature.collection.scaniddoc.scan.common.cameracontroller.CameraCallback
import com.innovation.rain.feature.collection.scaniddoc.scan.common.cameracontroller.CameraController
import com.innovation.rain.feature.collection.scaniddoc.scan.idcard.view.ScanIdCardView
import timber.log.Timber
import java.io.File
import javax.inject.Inject


class ScanIdCardPresenterImpl @Inject constructor(cameraController: CameraController,
                                                  view: ScanIdCardView)
    : BaseScanIdDocPresenterImpl<ScanIdCardView>(view, cameraController), ScanIdCardPresenter {

    private var isScanFirstTime = true

    private var firstFile: File? = null

    private var secondsFile: File? = null

    override fun getCameraCallback() = object : CameraCallback {
        override fun onSuccess(file: File) {
            if (isScanFirstTime) {
                firstFile = file
                isScanFirstTime = false
                view.showFlipCardMessage()
            } else {
                secondsFile = file
                isScanFirstTime = true
                view.showSuccessScreen()
            }
            Timber.i("Saved image at $file")
        }

        override fun onError(error: String) {
            //TODO: NTL
        }
    }
}
