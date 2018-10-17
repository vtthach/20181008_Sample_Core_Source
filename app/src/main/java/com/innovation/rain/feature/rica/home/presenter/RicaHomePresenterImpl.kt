package com.innovation.rain.feature.rica.home.presenter

import com.innovation.rain.app.base.presenter.BasePresenterImpl
import com.innovation.rain.feature.rica.home.view.RicaHomeView

import javax.inject.Inject


class RicaHomePresenterImpl @Inject
constructor(view: RicaHomeView) : BasePresenterImpl<RicaHomeView>(view), RicaHomePresenter {
}
