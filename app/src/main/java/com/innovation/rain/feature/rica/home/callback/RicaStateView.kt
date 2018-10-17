package com.innovation.rain.feature.rica.home.callback

interface RicaStateView {
    fun showPreLoadState()

    fun showLoadedState()

    fun showDoneState()

    fun onProceedButtonClicked()
}