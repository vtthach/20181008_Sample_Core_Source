package com.rain.carddispenser.exception

import com.sf0404.usbserialmonitor.DispenserStatus

class BarcodeException(var barcodeResult: String, var dispenserStatus: DispenserStatus) : Throwable() {
    override fun toString(): String {
        return "${javaClass.simpleName} - Barcode Error Value: $barcodeResult - Card Dispenser Status; $dispenserStatus"
    }
}
