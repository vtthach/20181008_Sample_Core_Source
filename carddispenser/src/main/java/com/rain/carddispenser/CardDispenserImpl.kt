package com.rain.carddispenser


import android.content.Context
import com.physicaloid.lib.Physicaloid
import com.physicaloid.lib.usb.driver.uart.UartConfig
import com.physicaloid.lib.usb.driver.uart.UartConfig.*
import com.rain.carddispenser.exception.CardDispenserErrorType
import com.rain.carddispenser.exception.CardDispenserException
import com.rain.carddispenser.util.HexUtils.intToHex
import com.sf0404.usbserialmonitor.DispenserStatus
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit

class CardDispenserImpl(var context: Context) : CardDispenser {

    private val statusResultType: Int = DISP_CHAR

    private val mReadLinefeedCode = LINEFEED_CODE_LF

    private var lastDataIs0x0D = false

    override fun checkAvailable(): Observable<Boolean> {
        // TODO check usb available
        return Observable.just(true)
    }

    override fun issueCard(): Observable<DispenserStatus> {
        return getExecuteObservable(DispenserConstants.ISSUE)
    }

    private fun getExecuteObservable(command: ByteArray): Observable<DispenserStatus> {
        return Observable.fromCallable<DispenserStatus> {
            executeCommand(command)
        }.timeout(5, TimeUnit.SECONDS)
    }

    override fun recallCard(): Observable<DispenserStatus> {
        return getExecuteObservable(DispenserConstants.RECALL)
    }

    override fun getDispenserStatus(): Observable<DispenserStatus> {
        return getExecuteObservable(DispenserConstants.GET_STATUS)
    }

    private fun executeCommand(command: ByteArray): DispenserStatus? {
        var pair = writeAndExecute(command)
        if (!DispenserConstants.GET_STATUS!!.contentEquals(command)) {
            Timber.tag(TAG).i("--- Start GET STATUS")
            pair = writeAndExecute(DispenserConstants.GET_STATUS)
        }
        val stringResult = getStringResult(pair, statusResultType)
        Timber.tag(TAG).i("--- Command result int string: $stringResult")
        return stringToDispenserStatus(stringResult)
    }

    private fun writeAndExecute(command: ByteArray): Pair<ByteArray, Int> {
        writeDataToSerial(command)
        var pair = readData(300)
        Timber.tag(TAG).i("--- Serial read data 1 : ${pair.second}")
        if (isCommandOk(pair)) {
            writeDataToSerial(DispenserConstants.EXECUTE)
            pair = readData(300)
            Timber.tag(TAG).i("--- Serial read data 12: ${pair.second}")
        }
        return pair
    }

    fun stringToDispenserStatus(stringResult: String?): DispenserStatus? {
        return if (stringResult != null) {
            DispenserStatus.find(stringResult)
        } else {
            val rs = DispenserStatus.UNKNOWN
            rs.id = "null"
            rs
        }
    }

    private fun getStringResult(pair: Pair<ByteArray, Int>, displayType: Int): String? {
        var stringRs: String? = null
        val rBuf = pair.first
        rBuf[pair.second] = 0
        if (pair.second > 0) {
            when (displayType) {
                DISP_CHAR -> {
                    stringRs = setSerialDataToString(displayType, rBuf, pair.second, "", "")
                }
                DISP_DEC -> {
                    stringRs = setSerialDataToString(displayType, rBuf, pair.second, "013", "010")
                }
                DISP_HEX -> {
                    stringRs = setSerialDataToString(displayType, rBuf, pair.second, "0d", "0a")
                }
            }
        }
        return stringRs
    }

    private fun isCommandOk(byte: Pair<ByteArray, Int>): Boolean {
        val rs = getStringResult(byte, DISP_HEX)
        val ok = DispenserConstants.ACK_RESPONSE == rs
        if (!ok) {
            throw CardDispenserException(CardDispenserErrorType.COMMAND_FAILED, "Command error $rs")
        }
        return ok
    }

    private fun readData(delaySleep: Long): Pair<ByteArray, Int> {
        if (delaySleep > 0) {
            Thread.sleep(delaySleep)
        }
        val rBuf = ByteArray(4096)
        val len = mSerial.read(rBuf)
        return Pair(rBuf, len)
    }

    private fun setSerialDataToString(disp: Int, rbuf: ByteArray, len: Int, sCr: String, sLf: String): String {
        var tmpBuf: Int
        var stringBuilder = StringBuilder()
        var i = 0
        while (i < len) {
            if (mReadLinefeedCode == LINEFEED_CODE_CR && rbuf[i].toInt() == 0x0D) {
                stringBuilder.append(sCr)
                stringBuilder.append(BR)
            } else if (mReadLinefeedCode == LINEFEED_CODE_LF && rbuf[i].toInt() == 0x0A) {
                stringBuilder.append(sLf)
                stringBuilder.append(BR)
            } else if (mReadLinefeedCode == LINEFEED_CODE_CRLF && rbuf[i].toInt() == 0x0D
                    && rbuf[i + 1].toInt() == 0x0A) {
                stringBuilder.append(sCr)
                if (disp != DISP_CHAR) {
                    stringBuilder.append(" ")
                }
                stringBuilder.append(sLf)
                stringBuilder.append(BR)
                ++i
            } else if (mReadLinefeedCode == LINEFEED_CODE_CRLF && rbuf[i].toInt() == 0x0D) {
                // case of rbuf[last] == 0x0D and rbuf[0] == 0x0A
                stringBuilder.append(sCr)
                lastDataIs0x0D = true
            } else if (lastDataIs0x0D && rbuf[0].toInt() == 0x0A) {
                if (disp != DISP_CHAR) {
                    stringBuilder.append(" ")
                }
                stringBuilder.append(sLf)
                stringBuilder.append(BR)
                lastDataIs0x0D = false
            } else if (lastDataIs0x0D && i != 0) {
                // only disable flag
                lastDataIs0x0D = false
                --i
            } else {
                when (disp) {
                    DISP_CHAR -> stringBuilder.append(rbuf[i].toChar())
                    DISP_DEC -> {
                        tmpBuf = rbuf[i].toInt()
                        if (tmpBuf < 0) {
                            tmpBuf += 256
                        }
                        stringBuilder.append(String.format("%1$03d", tmpBuf))
                        if (i < len - 1) {
                            stringBuilder.append(" ")
                        }
                    }
                    DISP_HEX -> {
                        stringBuilder.append(intToHex(rbuf[i].toInt()))
                        if (i < len - 1) {
                            stringBuilder.append(" ")
                        }
                    }
                    else -> {
                        // Use Hex as default
                        stringBuilder.append(intToHex(rbuf[i].toInt()))
                        if (i < len - 1) {
                            stringBuilder.append(" ")
                        }
                    }
                }
            }
            i++
        }
        return stringBuilder.toString()
    }

    override fun resetDispenser(): Observable<DispenserStatus> {
        return Observable.fromCallable<DispenserStatus> {
            executeCommand(DispenserConstants.RESET)
        }
    }

    override fun connect(): Observable<Boolean> {
        return Observable.fromCallable<Boolean> { openUsbSerial() }
    }

    override fun disconnect() {
        mSerial.close()
    }

    companion object {
        const val TAG = "vtt-CardDispenser"
        val BR = System.getProperty("line.separator")
        // Defines of Display Settings
        const val DISP_CHAR = 0
        const val DISP_DEC = 1
        const val DISP_HEX = 2

        // Linefeed Code Settings
        const val LINEFEED_CODE_CR = 0
        const val LINEFEED_CODE_CRLF = 1
        const val LINEFEED_CODE_LF = 2    // Defines of Display Settings
    }

    private val mSerial: Physicaloid = Physicaloid(context)

    private fun writeDataToSerial(arr: ByteArray) {
        if (!mSerial.isOpened) {
            updateConfig(mSerial)
            if (!mSerial.open()) {
                throw CardDispenserException(CardDispenserErrorType.DEVICE_IS_NOT_OPEN, "Device is not open")
            }
        }
        mSerial.write(arr, arr.size)
    }

    private fun openUsbSerial(): Boolean {
        Timber.tag(TAG).d("openUsbSerial")
        return if (!mSerial.isOpened) {
            if (!mSerial.open()) {
                throw  CardDispenserException(CardDispenserErrorType.CANNOT_OPEN, "Cannot open")
            } else {
                updateConfig(mSerial)
            }
            true
        } else {
            true
        }
    }

    private fun updateConfig(device: Physicaloid) {
        // TODO update config if any -> Currently the default value already applied at first when connect in open() method
        device.setConfig(UartConfig(9600, DATA_BITS8, STOP_BITS1, PARITY_NONE, false, false))
    }
}
