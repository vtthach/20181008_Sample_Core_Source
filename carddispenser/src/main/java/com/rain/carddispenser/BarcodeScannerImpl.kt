package com.rain.carddispenser

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import com.rain.carddispenser.exception.BarcodeException
import com.sf0404.usbserialmonitor.DispenserStatus
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit


class BarcodeScannerImpl(private val cardDispenser: CardDispenser, private val activity: Activity) : BarcodeScanner {

    companion object {
        private const val TAG: String = "vtt-Barcode"
        private val REGEX_BARCODE: Regex = Regex("^89\\d{17}")
        private const val DELAY_WAITING_BARCODE_RESULT: Long = 1 // In second
    }

    private val hiddenEditText: EditText = EditText(activity)

    init {
        hiddenEditText.layoutParams = getLayoutParam()
        hiddenEditText.setBackgroundColor(Color.RED)
        addToViewGroup(hiddenEditText, activity, Gravity.END)
        hiddenEditText.showSoftInputOnFocus = false
        hiddenEditText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && isEnableListen) {
                hiddenEditText.requestFocus()
            }
        }
        hiddenEditText.setOnKeyListener { v, keyCode, event ->
            onKeyEvent(event)
        }
    }

    private fun addToViewGroup(view: View, activity: Activity, gravity: Int) {
        val layout = activity.findViewById<View>(android.R.id.content).rootView as ViewGroup
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        val ll = LinearLayout(activity)
        ll.gravity = gravity
        ll.addView(view)
        layout.addView(ll, params)
    }

    private fun getLayoutParam(): ViewGroup.LayoutParams? {
        return ViewGroup.LayoutParams(1, 1)
    }

    private fun isHostActivityRunning(): Boolean {
        return !activity.isFinishing && !activity.isDestroyed
    }

    private fun onStartListener(): Observable<Unit>? {
        return Observable.fromCallable {
            Timber.tag(TAG).i("- OnStartListener - : ${Thread.currentThread().name}")
            isEnableListen = true
            hiddenEditText.requestFocus()
            latestBarcodeResult = StringBuilder()
        }
    }

    override fun startGetBarcode(): Observable<String> {
        Timber.tag(TAG).i("StartGetBarcode  - : ${Thread.currentThread().name}")
        return Observable.just(TAG)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    onStartListener()
                }
                .observeOn(Schedulers.io())
                .flatMap { cardDispenser.issueCard() }
                .observeOn(Schedulers.computation())
                .delay(DELAY_WAITING_BARCODE_RESULT, TimeUnit.SECONDS)
                .flatMap {
                    collectBarcodeResult(it)
                }
    }

    private fun stopKeyListener() {
        isEnableListen = false
    }

    private var latestBarcodeResult: StringBuilder? = null

    private var isEnableListen: Boolean = false

    private fun onKeyEvent(event: KeyEvent): Boolean {
        Timber.tag(TAG).i("- onKeyEvent -- keycode:${event.keyCode} -- keyDisplay :${event.displayLabel}")
        return if (isEnableListen) {
            if (isKeyAccepted(event)) {
                Timber.tag(TAG).i("- OnKeyAccepted ---displayLabel:${event.displayLabel} --keyCode: ${event.keyCode}")
                if (!isBarCodeValid(latestBarcodeResult!!)) {
                    latestBarcodeResult!!.append(event.displayLabel.toString())
                } else {
                    stopKeyListener()
                }
            }
            true
        } else {
            false
        }
    }

    private fun isKeyAccepted(key: KeyEvent): Boolean {
        var rs = false
        try {
            Integer.valueOf(key.displayLabel.toString())
            rs = true
        } catch (ignore: Exception) {
            // Ignore
        }
        return key.action == KeyEvent.ACTION_DOWN && rs
    }

    private fun isBarCodeValid(barcodeResult: CharSequence): Boolean {
        return barcodeResult.matches(REGEX_BARCODE)
    }

    private fun collectBarcodeResult(dispenserStatus: DispenserStatus): Observable<String> {
        return Observable.fromCallable<String> {
            Timber.tag(TAG).i("- CollectBarcodeResult ${Thread.currentThread().name}")
            stopKeyListener()
            getLatestBarcode(dispenserStatus)
        }
    }

    private fun getLatestBarcode(dispenserStatus: DispenserStatus): String? {
        Timber.tag(TAG).i("- LatestBarcode: ${latestBarcodeResult.toString()} - ${Thread.currentThread().name}")
        if (isBarCodeValid(latestBarcodeResult!!)) {
            return latestBarcodeResult.toString()
        } else {
            throw BarcodeException(latestBarcodeResult.toString(), dispenserStatus)
        }
    }

    override fun checkAvailable(): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
