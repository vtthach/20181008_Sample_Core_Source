package com.innovation.rain.feature.dashboard.callback

interface RicaStateView {
    fun onViewPreLoad()

    fun onViewLoaded()

    fun onViewDone()

    fun onProceedButtonClicked()
}