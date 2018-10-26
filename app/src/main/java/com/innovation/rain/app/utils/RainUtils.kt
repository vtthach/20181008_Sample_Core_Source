package com.innovation.rain.app.utils

import com.innovation.rain.app.header.HeaderBuilder

fun buildHeader(sessionId: String) = HeaderBuilder().sessionId(sessionId).build()