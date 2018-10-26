package com.rain.carddispenser.fake

import com.rain.carddispenser.CardDispenser
import com.rain.carddispenser.DispenserStatus
import io.reactivex.Observable

class FakeCardDispenserImpl : CardDispenser {
    override fun checkAvailable() = Observable.just(true)

    override fun issueCard() = Observable.just(true)

    override fun recallCard() = Observable.just(true)

    override fun getDispenserStatus() = Observable.just(DispenserStatus.DISPENSED)

    override fun resetDispenser() = Observable.just(true)

    override fun connect() = Observable.just(true)

    override fun disconnect() = Observable.just(true)
}