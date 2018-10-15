package com.innovation.rain.feature.collection.scaniddoc.scanidbook.presenter

import android.os.Bundle
import com.innovation.rain.app.base.presenter.BasePresenterImpl
import com.innovation.rain.feature.collection.scaniddoc.scanidbook.view.ScanIdBookView
import javax.inject.Inject


class ScanIdBookPresenterImpl @Inject
constructor(view: ScanIdBookView) : BasePresenterImpl<ScanIdBookView>(view), ScanIdBookPresenter {

    override fun onViewCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        super.onViewCreated(savedInstanceState, arguments)
    }
}
