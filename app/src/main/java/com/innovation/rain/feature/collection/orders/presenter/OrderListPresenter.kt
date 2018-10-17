package com.innovation.rain.feature.collection.orders.presenter

import com.innovation.rain.feature.collection.orders.model.OrderEntity
import com.sf0404.core.application.base.presenter.BasePresenter

interface OrderListPresenter : BasePresenter {
    fun loadOrderList(): List<OrderEntity>
}