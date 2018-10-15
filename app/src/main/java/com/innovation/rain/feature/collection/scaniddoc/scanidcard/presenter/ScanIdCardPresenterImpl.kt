package com.innovation.rain.feature.collection.scaniddoc.scanidcard.presenter

import android.os.Bundle
import com.innovation.rain.app.base.presenter.BasePresenterImpl
import com.innovation.rain.feature.collection.scaniddoc.scanidcard.view.ScanIdCardView
import javax.inject.Inject


class ScanIdCardPresenterImpl @Inject
constructor(view: ScanIdCardView) : BasePresenterImpl<ScanIdCardView>(view), ScanIdCardPresenter {

    override fun onViewCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        super.onViewCreated(savedInstanceState, arguments)
    }
}
