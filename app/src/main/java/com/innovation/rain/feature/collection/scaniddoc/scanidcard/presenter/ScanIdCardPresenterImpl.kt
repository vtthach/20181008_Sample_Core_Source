package com.innovation.rain.feature.collection.scaniddoc.scanidcard.presenter

import android.os.Bundle
import com.innovation.rain.app.base.presenter.BasePresenterImpl
import com.innovation.rain.app.widget.AutoFitTextureView
import com.innovation.rain.feature.collection.scaniddoc.cameracontroller.CameraCallback
import com.innovation.rain.feature.collection.scaniddoc.cameracontroller.CameraController
import com.innovation.rain.feature.collection.scaniddoc.scanidcard.view.ScanIdCardView
import timber.log.Timber
import java.io.File
import javax.inject.Inject


class ScanIdCardPresenterImpl @Inject constructor(private val cameraController: CameraController,
                                                  view: ScanIdCardView)
    : BasePresenterImpl<ScanIdCardView>(view), ScanIdCardPresenter {

    private var isScanFirstTime = true

    private var firstFile: File? = null

    private var secondsFile: File? = null

    private val cameraCallback = object : CameraCallback {
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
        cameraController.setCameraCallback(cameraCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraController.setCameraCallback(null)
    }
}
