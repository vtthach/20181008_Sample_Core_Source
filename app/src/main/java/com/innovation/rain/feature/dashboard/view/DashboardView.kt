package com.innovation.rain.feature.dashboard.view


import com.innovation.rain.app.base.presenter.BasePresenterView

interface DashboardView : BasePresenterView {
    fun enableButtonProceed(allowEnableProceedButton: Boolean)
}
