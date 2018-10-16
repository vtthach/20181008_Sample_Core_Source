package com.innovation.rain.feature.collection.scaniddoc.scanpassport.presenter

import android.os.Bundle
import com.innovation.rain.app.base.presenter.BasePresenterImpl
import com.innovation.rain.app.widget.AutoFitTextureView
import com.innovation.rain.feature.collection.scaniddoc.cameracontroller.CameraCallback
import com.innovation.rain.feature.collection.scaniddoc.cameracontroller.CameraController
import com.innovation.rain.feature.collection.scaniddoc.scanpassport.view.ScanPassportView
import timber.log.Timber
import java.io.File
import javax.inject.Inject


class ScanPassportPresenterImpl @Inject constructor(private val cameraController: CameraController,
                                                    view: ScanPassportView)
    : BasePresenterImpl<ScanPassportView>(view), ScanPassportPresenter {

    private var file: File? = null

    private val cameraCallback = object : CameraCallback {
        override fun onSuccess(file: File) {
            this@ScanPassportPresenterImpl.file = file
            view.showSuccessScreen()
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
