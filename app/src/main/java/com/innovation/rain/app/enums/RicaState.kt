package com.innovation.rain.app.enums

enum class RicaState(val id: Int) {
    STATE_PRE_LOADED(1),
    STATE_LOADED(2),
    STATE_DONE(3),
    UNKNOWN(-1);

    companion object {
        fun valueOf(value: Int): RicaState = RicaState.values().find { it.id == value } ?: UNKNOWN
    }
}