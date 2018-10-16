package com.innovation.rain.feature.agentlogin.view

import com.innovation.rain.R
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.dashboard.presenter.DashboardPresenter


class SampleFragment : BaseRicaFragment<DashboardPresenter>() {

    override fun getPresenter(): DashboardPresenter? {
        return null
    }

    override fun getLayoutId() = R.layout.fragment_sample


    override fun onRicaStatePreLoad() {
    }

    override fun onRicaStateLoaded() {
    }

    override fun onRicaStateDone() {
    }

    override fun onProceedButtonClicked() {
    }
}
