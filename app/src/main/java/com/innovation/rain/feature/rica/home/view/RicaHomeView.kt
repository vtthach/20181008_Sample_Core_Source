package com.innovation.rain.feature.rica.home.view


import android.support.v4.app.Fragment
import com.sf0404.core.application.base.presenter.BaseView

interface RicaHomeView : BaseView {
    fun enableButtonProceed(allowEnableProceedButton: Boolean)

    fun notifyRicaStateDone()

    fun goToNextScreen(classname: Class<out Fragment>)
}
