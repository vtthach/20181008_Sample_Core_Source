package com.innovation.rain.feature.agentlogin.view

import com.innovation.rain.R
import com.innovation.rain.feature.dashboard.base.BaseRicaFragment
import com.innovation.rain.feature.dashboard.presenter.DashboardPresenter


class SampleFragment : BaseRicaFragment<DashboardPresenter>() {

    override fun getPresenter(): DashboardPresenter? {
        return null
    }

    override fun getLayoutId() = R.layout.fragment_sample


    override fun onViewPreLoad() {
    }

    override fun onViewLoaded() {
    }

    override fun onViewDone() {
    }

    override fun onProceedButtonClicked() {
    }
}
