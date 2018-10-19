package com.innovation.rain.feature.rica.scaniddoc.scan.common.base.view

import com.sf0404.core.application.base.presenter.BaseView
import java.io.File

interface BaseScanIdDocView : BaseView {
    fun backToHomeRica()

    fun reviewImage(file: File)

    fun showTextureView(isShow: Boolean)
}