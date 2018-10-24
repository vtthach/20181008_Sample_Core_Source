package com.rain.carddispenser

import com.rain.carddispenser.model.SimEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CardDispenserControllerImpl(val cardDispenser: CardDispenser, val barcodeScanner: BarcodeScanner) : CardDispenserController {

    var sims: MutableList<SimEntity>? = null

    override fun init(sims: MutableList<SimEntity>) {
        this.sims = sims
    }

    override fun dispensing(callback: DispenseCallback) {
        if (sims != null && sims!!.size > 0) {
            val disposable = cardDispenser.checkAvailable()
                    .flatMap { barcodeScanner.checkAvailable() }
                    .flatMap { cardDispenser.issueCard() }
                    .delay(1500, TimeUnit.MILLISECONDS)
                    .flatMap { barcodeScanner.getBarcodeValue() }
                    .flatMap {
                        if (it != null && it.length > 0) {
                            cardDispenser.getDispenserStatus()
                        } else {
                            throw Exception()
                        }
                    }
                    .flatMap {
                        if (it == DispenserStatus.READY || it == DispenserStatus.TRAY_LOW || it == DispenserStatus.TRAY_EMPTY) {
                            Observable.just(it)
                        } else if (it == DispenserStatus.DISPENSED) {
                            Observable.just(it)
                                    .delay(1500, TimeUnit.MILLISECONDS)
                                    .flatMap {
                                        cardDispenser.getDispenserStatus()
                                                .flatMap {
                                                    if (it == DispenserStatus.READY || it == DispenserStatus.TRAY_LOW || it == DispenserStatus.TRAY_EMPTY) {
                                                        Observable.just(it)
                                                    } else if (it == DispenserStatus.DISPENSED) {
                                                        cardDispenser.getDispenserStatus()
                                                    } else {
                                                        throw Exception()
                                                    }
                                                }
                                    }
                        } else {
                            throw Exception()
                        }
                    }.observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        callback.onDispenseSuccess(sims!![0])
                        sims!!.removeAt(0)
                        if (sims!!.size == 0)
                            callback.onNoSimInQueue()
                    }, {
                        callback.onDispenseFail(sims!![0])
                        sims!!.removeAt(0)
                        if (sims!!.size == 0)
                            callback.onNoSimInQueue()
                    })
        }
    }
}