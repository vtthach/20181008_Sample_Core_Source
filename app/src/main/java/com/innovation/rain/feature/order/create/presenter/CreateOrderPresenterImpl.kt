package com.innovation.rain.feature.order.create.presenter

import com.innovation.rain.feature.order.create.view.CreateOrderView
import com.sf0404.core.application.base.presenter.BasePresenterImpl
import java.math.BigDecimal
import javax.inject.Inject


class CreateOrderPresenterImpl @Inject
constructor(view: CreateOrderView) : BasePresenterImpl<CreateOrderView>(view), CreateOrderPresenter {

    val minQuantity = 1
    val maxQuantity = 3
    val priceItem = "50.00"
    val numberOfDecimals = 2

    /*override fun handleSpinnerChange(name: String) {
        when (name) {
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
        }
    }*/

    override fun initPrice() {
        view.updateValueItemPrice("R" + priceItem)
        view.updateValueItemTotalPrice("R" + priceItem)
    }

    override fun handleCalculateButton(number: Int, isPlus: Boolean) {
        var quantity = number
        if (isPlus) {
            if (number < maxQuantity && number >= minQuantity) quantity++
        } else {
            if (number <= maxQuantity && number > minQuantity) quantity--
        }
        var totalPrice = calculatePrice(quantity, priceItem.toDouble())
        view.updateValueQuantity(quantity.toString())
        view.updateValueItemTotalPrice("R" + truncateDecimal(totalPrice, numberOfDecimals))
        view.updateValueSubTotalItem(quantity.toString())
    }

    private fun calculatePrice(quantity: Int, price: Double): Double {
        return quantity * price
    }

    private fun truncateDecimal(x: Double, numberofDecimals: Int): BigDecimal {
        return BigDecimal(x.toString()).setScale(numberofDecimals, BigDecimal.ROUND_FLOOR)
    }
}
