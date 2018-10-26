package com.sf0404.usbserialmonitor

enum class DispenserStatus(var id: String) {
    TRAY_EMPTY("SF008"),
    CARD_READY("SF000"),
    CARD_LOW("SF010"),
    CARD_DISPENSED("SF004"),
    ACK_RESPONSE("06"),
    UNKNOWN("unknown");

    override fun toString(): String {
        return super.toString() + "- $id"
    }

    companion object {
        fun find(value: String): DispenserStatus = DispenserStatus.values().find {
            value.contains(it.id, true)
        } ?: getUnknownStatus(value)

        private fun getUnknownStatus(value: String): DispenserStatus {
            val dispenserStatus = UNKNOWN
            dispenserStatus.id = value
            return dispenserStatus
        }
    }
}