package com.innovation.rain.feature.rica.home.view


import com.sf0404.core.application.base.presenter.BasePresenterView

interface RicaHomeView : BasePresenterView {
    fun enableButtonProceed(allowEnableProceedButton: Boolean)

    fun notifyRicaStateDone()
}
