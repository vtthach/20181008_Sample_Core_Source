package com.innovation.rain.feature.rica.home.callback

interface RicaStateView {
    fun onRicaStatePreLoad()

    fun onRicaStateLoaded()

    fun onRicaStateDone()

    fun onProceedButtonClicked()
}