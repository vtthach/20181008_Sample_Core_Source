package com.innovation.rain.feature.rica.home.view


import com.sf0404.core.application.base.presenter.BaseView

interface RicaHomeView : BaseView {
    fun enableButtonProceed(allowEnableProceedButton: Boolean)

    fun notifyRicaStateDone()
}
