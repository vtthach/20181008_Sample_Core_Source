package com.sf0404.usbserialmonitor

enum class DispenserStatus(var id: String) {
    TRAY_EMPTY("SF008"),
    CARD_READY("SF000"),
    CARD_LOW("SF010"),
    CARD_DISPENSED("SF004"),
    CARD_DISPENSING("SF800"),
    CARD_RECALLING("SF400"),
    CARD_AFTER_OUT("SF001"), // Use get card sometimes return this code
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