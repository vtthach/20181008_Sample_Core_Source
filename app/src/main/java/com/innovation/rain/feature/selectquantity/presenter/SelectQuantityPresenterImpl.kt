package com.innovation.rain.feature.selectquantity.presenter

import com.innovation.rain.feature.selectquantity.view.SelectQuantityView
import com.sf0404.core.application.base.presenter.BasePresenterImpl
import javax.inject.Inject


class SelectQuantityPresenterImpl @Inject
constructor(view: SelectQuantityView) : BasePresenterImpl<SelectQuantityView>(view), SelectQuantityPresenter {

    val minQuantity = 1
    val maxQuantity = 3

    override fun handleSpinnerChange(name: String) {
        /*when (name) {
            "R50 once off" -> {
                view.updateValueItemName("SIM +5 data days")
                view.updateValueItemNameDesc("R50 a gig")
                view.updateValueItemPrice("50.0")
                view.updateValueItemTotalPrice("50.0")
                view.updateValueQuantity("1")
            }
            "R100 once off" -> {
                view.updateValueItemName("SIM +10 data days")
                view.updateValueItemNameDesc("R100 a gig")
                view.updateValueItemPrice("100.0")
                view.updateValueItemTotalPrice("100.0")
                view.updateValueQuantity("1")
            }
            "R200 once off" -> {
                view.updateValueItemName("SIM +20 data days")
                view.updateValueItemNameDesc("R200 a gig")
                view.updateValueItemPrice("200.0")
                view.updateValueItemTotalPrice("200.0")
                view.updateValueQuantity("1")
            }
            "R300 once off" -> {
                view.updateValueItemName("SIM +30 data days")
                view.updateValueItemNameDesc("R300 a gig")
                view.updateValueItemPrice("300.0")
                view.updateValueItemTotalPrice("300.0")
                view.updateValueQuantity("1")
            }
            else -> {
                view.updateValueItemName("")
                view.updateValueItemNameDesc("")
                view.updateValueItemPrice("")
                view.updateValueItemTotalPrice("")
                view.updateValueQuantity("1")
            }
        }*/
    }

    override fun handleCalculateButton(number: Int, price: Double, isPlus: Boolean) {
        var quantity = number
        if(isPlus) {
            if (number < maxQuantity && number >= minQuantity) quantity++
        } else {
            if(number <= maxQuantity && number > minQuantity) quantity--
        }
        view.updateValueQuantity(quantity.toString())
        view.updateValueItemTotalPrice(calculatePrice(quantity.toString().toDouble(), price).toString())
        view.updateValueSubTotalItem(quantity.toString())
    }

    private fun calculatePrice(quantity: Double, price : Double) : Double {
        return quantity * price
    }
}
