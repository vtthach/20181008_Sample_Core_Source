package com.innovation.rain.feature.selectQuantity.presenter


import com.innovation.rain.feature.selectQuantity.view.SelectQuantityView
import com.sf0404.core.application.base.presenter.BasePresenterImpl

import javax.inject.Inject


class SelectQuantityPresenterImpl @Inject
constructor(view: SelectQuantityView) : BasePresenterImpl<SelectQuantityView>(view), SelectQuantityPresenter {


}
