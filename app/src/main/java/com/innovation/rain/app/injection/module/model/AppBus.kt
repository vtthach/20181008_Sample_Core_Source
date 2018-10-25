package com.innovation.rain.app.injection.module.model

import com.innovation.rain.feature.collection.orders.model.OrderEntity

data class AppBus(var orderList: List<OrderEntity>? = null,
                  var sessionId: String = "")