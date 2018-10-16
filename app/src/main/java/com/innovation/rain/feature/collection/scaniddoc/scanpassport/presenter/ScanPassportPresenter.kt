package com.innovation.rain.feature.collection.scaniddoc.scanpassport.presenter

import com.innovation.rain.app.base.presenter.BasePresenter
import com.innovation.rain.app.widget.AutoFitTextureView

interface ScanPassportPresenter : BasePresenter {
    fun capture()

    fun setTextureView(autoFitTextureView: AutoFitTextureView)
}