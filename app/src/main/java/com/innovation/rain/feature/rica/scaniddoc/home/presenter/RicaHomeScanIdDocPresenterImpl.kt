package com.innovation.rain.feature.rica.scaniddoc.home.presenter

import com.innovation.rain.app.base.presenter.BasePresenterImpl
import com.innovation.rain.feature.rica.scaniddoc.home.view.RicaHomeScanIdDocView
import javax.inject.Inject

class RicaHomeScanIdDocPresenterImpl @Inject constructor(view: RicaHomeScanIdDocView)
    : BasePresenterImpl<RicaHomeScanIdDocView>(view), RicaHomeScanIdDocPresenter