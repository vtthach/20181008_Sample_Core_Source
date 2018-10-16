package com.innovation.rain.feature.rica.dashboard.presenter

import com.innovation.rain.app.base.presenter.BasePresenterImpl
import com.innovation.rain.feature.rica.dashboard.view.DashboardView
import com.sf0404.common.utils.ValidationUtils

import javax.inject.Inject


class DashboardPresenterImpl @Inject
constructor(view: DashboardView) : BasePresenterImpl<DashboardView>(view), DashboardPresenter {
}
