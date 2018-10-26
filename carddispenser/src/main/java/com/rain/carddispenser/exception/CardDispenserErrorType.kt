package com.rain.carddispenser.exception

enum class CardDispenserErrorType {
    COMMAND_FAILED,
    CANNOT_OPEN,
    UNKNOWN_ERROR,
    DEVICE_IS_NOT_OPEN,
    TIME_OUT //     // Timeout occur after MAX_WAITING_TIME config
}
