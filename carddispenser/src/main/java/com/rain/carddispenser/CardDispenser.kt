package com.rain.carddispenser

import io.reactivex.Observable

interface CardDispenser {
    fun checkAvailable(): Observable<Boolean>

    fun issueCard(): Observable<Boolean>

    fun recallCard(): Observable<Boolean>

    fun getDispenserStatus(): Observable<DispenserStatus>

    fun resetDispenser(): Observable<Boolean>

    fun connect(): Observable<Boolean>

    fun disconnect(): Observable<Boolean>
}