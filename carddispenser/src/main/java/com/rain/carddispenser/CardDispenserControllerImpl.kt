package com.rain.carddispenser

import com.rain.carddispenser.exception.*
import com.rain.carddispenser.model.SimEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CardDispenserControllerImpl(val cardDispenser: CardDispenser, val barcodeScanner: BarcodeScanner) : CardDispenserController {

    private var disposable: Disposable? = null

    private var timetamp: Long = 0 // in milliseconds

    private val MAX_WAITING_TIME = 100 * 1000 // in milliseconds

    private var sims: MutableList<SimEntity>? = null

    override fun init(sims: MutableList<SimEntity>) {
        this.sims = sims
    }

    override fun dispensing(callback: DispenseCallback) {
        if (sims != null && sims!!.size > 0) {
            disposable = cardDispenser.checkAvailable()
                    .flatMap { isCardDispenserAvailable ->
                        if (isCardDispenserAvailable) {
                            barcodeScanner.checkAvailable()
                        } else {
                            throw CardDispenserNotAvailableException()
                        }
                    }.flatMap { isBarcodeScannerAvailable ->
                        if (isBarcodeScannerAvailable) {
                            cardDispenser.issueCard()
                        } else {
                            throw BarcodeScannerNotAvailableException()
                        }
                    }.flatMap { isIssueCardSuccess ->
                        if (isIssueCardSuccess) {
                            timetamp = System.currentTimeMillis()
                            Observable.timer(2000, TimeUnit.MILLISECONDS)
                        } else {
                            throw IssueCardException()
                        }
                    }.flatMap {
                        barcodeScanner.getBarcodeValue()
                    }.flatMap { iccid ->
                        if (iccid != null && iccid.length > 0) {
                            sims!![0].iccid = iccid
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
        return cardDispenser.getDispenserStatus()
                .flatMap { dispenserStatus ->
                    if (dispenserStatus == DispenserStatus.DISPENSED && (System.currentTimeMillis() - timetamp < MAX_WAITING_TIME)) {
                        throw DispenseStatusRetryException()
                    } else if (dispenserStatus == DispenserStatus.READY || dispenserStatus == DispenserStatus.TRAY_EMPTY || dispenserStatus == DispenserStatus.TRAY_LOW) {
                        if (System.currentTimeMillis() - timetamp < MAX_WAITING_TIME) {
                            Observable.just(dispenserStatus)
                        } else {
                            throw CardReCallException()
                        }
                    } else {
                        throw Exception()
                    }
                }.retryWhen { throwableObservable ->
                    throwableObservable.flatMap { throwable ->
                        if (throwable is DispenseStatusRetryException) {
                            Observable.timer(250, TimeUnit.MILLISECONDS)
                        } else Observable.error<Any>(throwable)
                    }
                }
    }

    override fun destroy() {
        disposable?.dispose()
    }
}