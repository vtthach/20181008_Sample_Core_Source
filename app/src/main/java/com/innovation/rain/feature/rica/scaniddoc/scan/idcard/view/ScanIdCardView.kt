package com.innovation.rain.feature.rica.scaniddoc.scan.idcard.view

import com.innovation.rain.app.base.presenter.BasePresenterView

interface ScanIdCardView : BasePresenterView {
    fun showSuccessScreen()

    fun showFlipCardMessage()
}
