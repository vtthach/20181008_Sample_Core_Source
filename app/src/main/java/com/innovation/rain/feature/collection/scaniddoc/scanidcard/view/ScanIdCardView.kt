package com.innovation.rain.feature.collection.scaniddoc.scanidcard.view

import com.innovation.rain.app.base.presenter.BasePresenterView

interface ScanIdCardView : BasePresenterView {
    fun showSuccessScreen()

    fun showFlipCardMessage()
}
