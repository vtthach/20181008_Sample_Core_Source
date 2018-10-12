package com.innovation.rain.feature.collection.orders.presenter

import android.os.Bundle
import com.innovation.rain.app.base.presenter.BasePresenterImpl
import com.innovation.rain.feature.collection.orders.model.OrderEntity
import com.innovation.rain.feature.collection.orders.view.OrderListView

import javax.inject.Inject


class OrderListPresenterImpl @Inject
constructor(view: OrderListView) : BasePresenterImpl<OrderListView>(view), OrderListPresenter {
    override fun onViewCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        super.onViewCreated(savedInstanceState, arguments)
        view.showList(loadOrderList())
    }

    override fun loadOrderList(): List<OrderEntity> {

        //TODO: remove mock data
        return listOf<OrderEntity>(OrderEntity("Sim 1", "No spend limit set as pay as you use "),
                OrderEntity("Sim 2", "No spend limit set as pay as you use "),
//                OrderEntity("Sim 3", "No spend limit set as pay as you use "),
//                OrderEntity("Sim 4", "No spend limit set as pay as you use "),
//                OrderEntity("Sim 5", "No spend limit set as pay as you use "),
//                OrderEntity("Sim 6", "No spend limit set as pay as you use "),
//                OrderEntity("Sim 7", "No spend limit set as pay as you use "),
//                OrderEntity("Sim 8", "No spend limit set as pay as you use "),
                OrderEntity("Sim 9", "No spend limit set as pay as you use ")
        )
    }

}
