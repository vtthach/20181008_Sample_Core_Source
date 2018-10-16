package com.innovation.rain.feature.collection.scaniddoc.scanidcard.presenter

import com.innovation.rain.app.base.presenter.BasePresenter
import com.innovation.rain.app.widget.AutoFitTextureView

interface ScanIdCardPresenter : BasePresenter {
    fun capture()

    fun setTextureView(autoFitTextureView: AutoFitTextureView)
}