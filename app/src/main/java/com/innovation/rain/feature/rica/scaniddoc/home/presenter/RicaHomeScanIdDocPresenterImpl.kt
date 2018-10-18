package com.innovation.rain.feature.rica.scaniddoc.home.presenter

import com.innovation.rain.feature.rica.scaniddoc.home.view.RicaHomeScanIdDocView
import com.sf0404.core.application.base.presenter.BasePresenterImpl
import javax.inject.Inject

class RicaHomeScanIdDocPresenterImpl @Inject constructor(view: RicaHomeScanIdDocView)
    : BasePresenterImpl<RicaHomeScanIdDocView>(view), RicaHomeScanIdDocPresenter