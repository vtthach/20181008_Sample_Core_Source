package com.innovation.rain.feature.collection.simdispenser.presenter

import com.sf0404.core.application.base.presenter.BasePresenterImpl

import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserView

import javax.inject.Inject


class SimDispenserPresenterImpl @Inject
constructor(view: SimDispenserView) : BasePresenterImpl<SimDispenserView>(view), SimDispenserPresenter {


}
