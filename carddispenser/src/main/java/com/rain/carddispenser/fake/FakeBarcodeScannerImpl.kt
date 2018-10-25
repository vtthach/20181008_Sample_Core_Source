package com.rain.carddispenser.fake

import com.rain.carddispenser.BarcodeScanner
import io.reactivex.Observable

class FakeBarcodeScannerImpl: BarcodeScanner {

    override fun checkAvailable() = Observable.just(true)

    override fun getBarcodeValue(): Observable<String> = Observable.just("840001234")
}