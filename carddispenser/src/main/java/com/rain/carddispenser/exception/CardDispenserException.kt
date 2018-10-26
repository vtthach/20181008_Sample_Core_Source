package com.rain.carddispenser.exception

// External exception
class CardDispenserException(errorType: CardDispenserErrorType, message: String?) : RuntimeException(message) {
    val errorMsg: String? = message
}
