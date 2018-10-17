package com.innovation.rain.feature.collection.orders.presenter

import com.innovation.rain.app.base.presenter.BasePresenter
import com.innovation.rain.feature.collection.orders.model.OrderEntity

interface OrderListPresenter : BasePresenter {
    fun loadOrderList(): List<OrderEntity>
}