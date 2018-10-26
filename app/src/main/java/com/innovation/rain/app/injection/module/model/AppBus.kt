package com.innovation.rain.app.injection.module.model

import com.innovation.rain.feature.collection.orders.model.OrderEntity

data class AppBus(var orderList: List<OrderEntity>? = null, var flow: Flow = Flow.UNDEFINED)

enum class Flow {
    SHOP,
    COLLECT,
    UNDEFINED
}