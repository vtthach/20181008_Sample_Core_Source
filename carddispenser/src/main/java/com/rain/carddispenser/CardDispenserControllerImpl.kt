package com.rain.carddispenser

import com.rain.carddispenser.exception.*
import com.rain.carddispenser.model.SimEntity
import com.sf0404.usbserialmonitor.DispenserStatus
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class CardDispenserControllerImpl(val cardDispenser: CardDispenser, val barcodeScanner: BarcodeScanner) : CardDispenserController {

    private var disposable: Disposable? = null

    private var timetamp: Long = 0 // in milliseconds

    private val MAX_WAITING_TIME = 10 * 1000L // in milliseconds

    private val WAITING_FOR_BARCODE_READ = 1 * 1000L // in milliseconds

    private val CHECK_DISPENSER_STATUS_DURATION = 250L // in milliseconds

    private var sims: MutableList<SimEntity>? = null

    override fun init(sims: MutableList<SimEntity>) {
        this.sims = sims
    }

    override fun disconnect() {
        cardDispenser.disconnect()
    }

    override fun connect() {
        cardDispenser.connect()
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
                            barcodeScanner.startGetBarcode()
                        } else {
                            throw BarcodeScannerNotAvailableException()
                        }
                    }.flatMap { iccId ->
                        Timber.i("vtt result IccId:  $iccId ${Thread.currentThread().name}")
                        timetamp = System.currentTimeMillis()
                        if (iccId.isNotEmpty()) {
                            sims!![0].iccid = iccId
                            getCheckStatusObservable()
                        } else {
                            // TODO  BarcodeValueException  - Auto recall and issue new card if cannot read barcode
                            throw BarcodeValueException()
                        }
                    }.onErrorResumeNext(Function { it ->
                        if (it is CardDispenserTimeoutException) {
                            Timber.i(it, "SF Exception1 :  ${it.message}")
                            cardDispenser.recallCard().map { throw  CardDispenserException(CardDispenserErrorType.TIME_OUT, "Timeout -> Auto Recall") }
                        } else {
                            throw it
                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        callback.onDispenseSuccess(sims!![0])
                        sims!!.removeAt(0)
                        if (sims!!.size == 0)
                            callback.onNoSimInQueue()
                    }, {
                        Timber.i(it, "SF Exception2 :  ${it.message}")
                        callback.onDispenseFail(sims!![0], it.message)
                        sims!!.removeAt(0)
                        if (sims!!.size == 0)
                            callback.onNoSimInQueue()
                    })
        }
    }

    private fun getCheckStatusObservable(): Observable<*> {
        return cardDispenser.getDispenserStatus()
                .flatMap { dispenserStatus ->
                    Timber.i("SF getCheckStatusObservable:  $dispenserStatus ${Thread.currentThread().name}")
                    if (isCardOut(dispenserStatus)) {
                        if (System.currentTimeMillis() - timetamp < MAX_WAITING_TIME) {
                            throw DispenseStatusRetryException()
                        } else {
                            throw CardDispenserTimeoutException()
                        }
                    } else if (dispenserStatus == DispenserStatus.CARD_READY || dispenserStatus == DispenserStatus.TRAY_EMPTY || dispenserStatus == DispenserStatus.CARD_LOW) {
                        if (System.currentTimeMillis() - timetamp < MAX_WAITING_TIME) {
                            Observable.just(dispenserStatus)
                        } else {
                            throw CardAutoReCallException()
                        }
                    } else {
                        throw CardDispenserException(CardDispenserErrorType.UNKNOWN_ERROR, dispenserStatus.id)
                    }
                }.retryWhen { throwableObservable ->
                    throwableObservable.flatMap { throwable ->
                        if (throwable is DispenseStatusRetryException) {
                            Observable.timer(CHECK_DISPENSER_STATUS_DURATION, TimeUnit.MILLISECONDS)
                        } else Observable.error<Any>(throwable)
                    }
                }
    }

    private fun isCardOut(dispenserStatus: DispenserStatus): Boolean {
        return dispenserStatus == DispenserStatus.CARD_DISPENSED || dispenserStatus == DispenserStatus.CARD_DISPENSING || dispenserStatus == DispenserStatus.CARD_AFTER_OUT
    }

    override fun destroy() {
        disposable?.dispose()
    }
}