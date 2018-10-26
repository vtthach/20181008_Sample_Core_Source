package com.rain.carddispenser

import com.sf0404.usbserialmonitor.DispenserStatus
import io.reactivex.Observable

interface CardDispenser {
    fun checkAvailable(): Observable<Boolean>

    fun issueCard(): Observable<DispenserStatus>

    fun recallCard(): Observable<DispenserStatus>

    fun getDispenserStatus(): Observable<DispenserStatus>

    fun resetDispenser(): Observable<DispenserStatus>

    fun connect(): Observable<Boolean>

    fun disconnect()
}
