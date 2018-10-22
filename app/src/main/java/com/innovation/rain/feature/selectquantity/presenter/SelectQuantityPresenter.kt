package com.innovation.rain.feature.selectquantity.presenter

import com.sf0404.core.application.base.presenter.BasePresenter

interface SelectQuantityPresenter : BasePresenter{

    fun handleSpinnerChange(name: String)
    fun handleCalculateButton(quantity: Int, price: Double, isPlus: Boolean)
}