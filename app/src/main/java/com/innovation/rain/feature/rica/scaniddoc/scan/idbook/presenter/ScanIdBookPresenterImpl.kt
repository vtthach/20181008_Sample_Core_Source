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

    private var file: File? = null

    override fun getCameraCallback() = object : CameraCallback {
        override fun onSuccess(file: File) {
            this@ScanIdBookPresenterImpl.file = file
            view.backToHomeRica()
            Timber.i("Saved image at $file")
        }

        override fun onError(error: String) {
            //TODO: NTL
        }
    }

}
