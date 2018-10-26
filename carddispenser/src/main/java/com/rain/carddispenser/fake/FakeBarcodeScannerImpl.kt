package com.rain.carddispenser.fake

import com.rain.carddispenser.BarcodeScanner
import io.reactivex.Observable

class FakeBarcodeScannerImpl: BarcodeScanner {

    override fun startGetBarcode(): Observable<String> {
        return Observable.just("89000000000000001")
    }

    override fun checkAvailable() = Observable.just(true)!!
}