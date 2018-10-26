package com.rain.carddispenser

import com.rain.carddispenser.model.SimEntity

interface CardDispenserController {
    fun init(sims: MutableList<SimEntity>)

    fun dispensing(callback: DispenseCallback)

    fun destroy()
    fun connect()
    fun disconnect()
}