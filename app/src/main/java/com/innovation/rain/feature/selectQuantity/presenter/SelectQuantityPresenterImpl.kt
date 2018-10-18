package com.innovation.rain.feature.selectQuantity.presenter

import com.innovation.rain.app.base.presenter.BasePresenterImpl

import com.innovation.rain.feature.selectQuantity.view.SelectQuantityView

import javax.inject.Inject


class SelectQuantityPresenterImpl @Inject
constructor(view: SelectQuantityView) : BasePresenterImpl<SelectQuantityView>(view), SelectQuantityPresenter {


}
