package com.rain.carddispenser

import com.rain.carddispenser.model.SimEntity

interface DispenseCallback {
    fun onDispenseSuccess(simEntity: SimEntity)

    fun onDispenseFail(simEntity: SimEntity, errorCode: String?)

    fun onNoSimInQueue()
}