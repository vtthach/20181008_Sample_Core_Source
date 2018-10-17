package com.innovation.rain.feature.rica.home.view


import com.innovation.rain.app.base.presenter.BasePresenterView

interface RicaHomeView : BasePresenterView {
    fun enableButtonProceed(allowEnableProceedButton: Boolean)

    fun notifyRicaStateDone()
}
