package com.innovation.rain.feature.rica.home.presenter

import com.sf0404.core.application.base.presenter.BasePresenterImpl
import com.innovation.rain.feature.rica.home.view.RicaHomeView

import javax.inject.Inject


class RicaHomePresenterImpl @Inject
constructor(view: RicaHomeView) : BasePresenterImpl<RicaHomeView>(view), RicaHomePresenter {
}
