package com.innovation.rain.feature.rica.scaniddoc.scan.common.base.presenter

import com.innovation.rain.app.widget.AutoFitTextureView
import com.sf0404.core.application.base.presenter.BasePresenter

interface BaseScanIdDocPresenter : BasePresenter {
    fun capture()

    fun setTextureView(autoFitTextureView: AutoFitTextureView)
}