package com.innovation.rain.feature.rica.dashboard.callback

interface RicaStateView {
    fun onRicaStatePreLoad()

    fun onRicaStateLoaded()

    fun onRicaStateDone()

    fun onProceedButtonClicked()
}