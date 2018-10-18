package com.innovation.rain.feature.collection.orders.view


import com.innovation.rain.feature.collection.orders.model.OrderEntity
import com.sf0404.core.application.base.presenter.BasePresenterView

interface OrderListView : BasePresenterView {

    fun showList(list: List<OrderEntity>)
}
