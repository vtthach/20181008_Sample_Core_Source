package com.innovation.rain.feature.collection.orders.view


import com.sf0404.core.application.base.presenter.BasePresenterView
import com.innovation.rain.feature.collection.orders.model.OrderEntity

interface OrderListView : BasePresenterView {

    fun showList(list: List<OrderEntity>)
}
