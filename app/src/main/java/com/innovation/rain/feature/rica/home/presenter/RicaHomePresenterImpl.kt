package com.innovation.rain.feature.rica.home.presenter

import com.innovation.rain.feature.rica.home.view.RicaHomeView
import com.sf0404.core.application.base.presenter.BasePresenterImpl
import javax.inject.Inject


class RicaHomePresenterImpl @Inject
constructor(view: RicaHomeView) : BasePresenterImpl<RicaHomeView>(view), RicaHomePresenter {

    override fun dispensing() {
        view.goToSimDispenserScreen()
    }
}
