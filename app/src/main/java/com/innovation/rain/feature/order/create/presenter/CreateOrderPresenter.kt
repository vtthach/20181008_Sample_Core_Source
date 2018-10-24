package com.innovation.rain.feature.order.create.presenter

import com.sf0404.core.application.base.presenter.BasePresenter

interface CreateOrderPresenter : BasePresenter{

    //fun handleSpinnerChange(name: String)
    fun handleCalculateButton(quantity: Int, isPlus: Boolean)
    fun initPrice()
}