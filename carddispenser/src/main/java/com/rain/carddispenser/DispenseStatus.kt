package com.rain.carddispenser

enum class DispenserStatus(val id: Int) {
    TRAY_EMPTY(8), READY(0), DISPENSED(4), TRAY_LOW(10)
}