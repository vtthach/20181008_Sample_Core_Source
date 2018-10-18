package com.innovation.rain.feature.rica.scaniddoc.scan.common.base.presenter

import com.innovation.rain.app.base.presenter.BasePresenter
import com.innovation.rain.app.widget.AutoFitTextureView

interface BaseScanIdDocPresenter : BasePresenter {
    fun capture()

    fun setTextureView(autoFitTextureView: AutoFitTextureView)
}