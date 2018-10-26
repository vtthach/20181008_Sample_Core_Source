package com.innovation.rain.app.header

class HeaderBuilder {

    companion object {
        const val SESSION_ID = "sessionId"
    }

    private val map = HashMap<String, String>()

    fun sessionId(sessionId: String) = apply { map[SESSION_ID] = sessionId }

//    fun timestamp(timestamp: String) = apply { map[TIMESTAMP] = timestamp }

    fun build() = map
}