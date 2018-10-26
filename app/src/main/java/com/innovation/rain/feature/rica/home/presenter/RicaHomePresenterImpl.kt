package com.innovation.rain.feature.rica.home.presenter

import com.innovation.rain.app.injection.module.model.AppBus
import com.innovation.rain.app.injection.module.model.Flow
import com.innovation.rain.feature.account.create.view.CreateAccountFragment
import com.innovation.rain.feature.collection.signin.view.SimDispenserFragment
import com.innovation.rain.feature.rica.home.view.RicaHomeView
import com.sf0404.core.application.base.presenter.BasePresenterImpl
import timber.log.Timber
import javax.inject.Inject


class RicaHomePresenterImpl @Inject
constructor(view: RicaHomeView, var appBus: AppBus) : BasePresenterImpl<RicaHomeView>(view), RicaHomePresenter {

    override fun dispensing() {
        when (appBus.flow) {
            Flow.SHOP -> view.goToNextScreen(CreateAccountFragment::class.java)
            Flow.COLLECT -> view.goToNextScreen(SimDispenserFragment::class.java)
            else -> {
                Timber.i("Undefined flow")
            }
        }

    }
}
