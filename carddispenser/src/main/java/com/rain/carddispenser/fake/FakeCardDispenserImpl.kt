package com.rain.carddispenser.fake

import com.rain.carddispenser.CardDispenser
import com.sf0404.usbserialmonitor.DispenserStatus
import io.reactivex.Observable

class FakeCardDispenserImpl : CardDispenser {
    override fun recallCard(): Observable<DispenserStatus> {
        return Observable.just(DispenserStatus.CARD_READY)
    }

    override fun resetDispenser(): Observable<DispenserStatus> {
        return Observable.just(DispenserStatus.CARD_READY)
    }

    override fun disconnect() {
        // Do nothing
    }

    override fun issueCard(): Observable<DispenserStatus> {
        return Observable.just(DispenserStatus.CARD_READY)
    }

    override fun checkAvailable() = Observable.just(true)!!

    override fun getDispenserStatus() = Observable.just(DispenserStatus.CARD_LOW)!!

    override fun connect() = Observable.just(true)!!
}