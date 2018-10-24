package com.innovation.rain.feature.order.create.presenter

import android.os.Bundle
import com.innovation.rain.feature.order.create.view.CreateOrderView
import com.sf0404.core.application.base.presenter.BasePresenterImpl
import java.math.BigDecimal
import javax.inject.Inject


class CreateOrderPresenterImpl @Inject
constructor(view: CreateOrderView) : BasePresenterImpl<CreateOrderView>(view), CreateOrderPresenter {

    val minQuantity = 1
    val maxQuantity = 3
    val itemPrice = "50.00"
    val numberOfDecimals = 2

    override fun initPrice() {
        view.updateValueItemPrice("R" + itemPrice)
        view.updateValueItemTotalPrice("R" + itemPrice)
    }

    override fun onViewCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        super.onViewCreated(savedInstanceState, arguments)
        initPrice()
    }

    override fun handleCalculateButton(number: Int, isPlus: Boolean) {
        var quantity = (number + if (isPlus) { 1 } else { -1 }).coerceIn(1, 3)
        var totalPrice = calculatePrice(quantity, itemPrice.toDouble())
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
