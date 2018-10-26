package com.rain.carddispenser

import io.reactivex.Observable

interface BarcodeScanner {
    fun checkAvailable(): Observable<Boolean>
    fun startGetBarcode(): Observable<String>
}