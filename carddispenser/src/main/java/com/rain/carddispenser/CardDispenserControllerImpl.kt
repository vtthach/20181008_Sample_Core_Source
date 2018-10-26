package com.rain.carddispenser

import com.rain.carddispenser.exception.*
import com.rain.carddispenser.model.SimEntity
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CardDispenserControllerImpl(val cardDispenser: CardDispenser, val barcodeScanner: BarcodeScanner) : CardDispenserController {

    private var disposable: Disposable? = null

    private var timetamp: Long = 0

    var sims: MutableList<SimEntity>? = null

    override fun init(sims: MutableList<SimEntity>) {
        this.sims = sims
    }

    override fun dispensing(callback: DispenseCallback) {
        if (sims != null && sims!!.size > 0) {
            disposable = cardDispenser.checkAvailable()
                    .flatMap {
                        if (it) {
                            barcodeScanner.checkAvailable()
                        } else {
                            throw CardDispenserNotAvailableException()
                        }
                    }
                    .flatMap {
                        if (it) {
                            cardDispenser.issueCard()
                        } else {
                            throw BarcodeScannerNotAvailableException()
                        }
                    }.flatMap {
                        if (it) {
                            timetamp = System.currentTimeMillis()
                            Observable.just(it)
                        } else {
                            throw IssueCardException()
                        }
                    }
                    .delay(2000, TimeUnit.MILLISECONDS)
                    .flatMap { barcodeScanner.getBarcodeValue() }
                    .flatMap {
                        if (it != null && it.length > 0) {
                            sims!![0].iccid = it
                            getCheckStatusObservable()
                        } else {
                            throw BarcodeValueException()
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


    private fun getCheckStatusObservable(): Observable<*> {
        return cardDispenser.getDispenserStatus().flatMap { dispenserStatus ->
            if (dispenserStatus === DispenserStatus.DISPENSED && System.currentTimeMillis() - timetamp < 100000) {
                throw DispenseRetryException()
            } else if (dispenserStatus === DispenserStatus.READY || dispenserStatus === DispenserStatus.TRAY_EMPTY || dispenserStatus === DispenserStatus.TRAY_LOW) {
                if (System.currentTimeMillis() - timetamp < 100000) {
                    Observable.just(dispenserStatus)
                } else {
                    throw Exception()
                }
            } else {
                throw Exception()
            }
        }.retryWhen { throwableObservable ->
            throwableObservable
                    .flatMap { throwable ->
                        if (throwable is DispenseRetryException) {
                            Observable.timer(250,
                                    TimeUnit.MILLISECONDS)
                        } else Observable.error<Any>(throwable)
                    }
        }
    }

    override fun destroy() {
        disposable?.dispose()
    }
}