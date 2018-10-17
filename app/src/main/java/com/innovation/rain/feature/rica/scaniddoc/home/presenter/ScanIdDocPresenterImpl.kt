package com.innovation.rain.feature.rica.scaniddoc.home.presenter

import android.os.Bundle
import com.innovation.rain.app.base.presenter.BasePresenterImpl
import com.innovation.rain.feature.rica.scaniddoc.home.view.ScanIdDocView
import javax.inject.Inject

class ScanIdDocPresenterImpl @Inject constructor(view: ScanIdDocView)
    : BasePresenterImpl<ScanIdDocView>(view), ScanIdDocPresenter {

    override fun onViewCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        super.onViewCreated(savedInstanceState, arguments)
    }
}