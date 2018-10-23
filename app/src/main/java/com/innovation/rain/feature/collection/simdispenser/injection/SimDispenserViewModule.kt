package com.innovation.rain.feature.collection.simdispenser.injection

import com.innovation.rain.feature.collection.simdispenser.presenter.SimDispenserPresenter
import com.innovation.rain.feature.collection.simdispenser.presenter.SimDispenserPresenterImpl
import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserFragment
import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserView

import com.sf0404.core.application.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class SimDispenserViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: SimDispenserFragment): SimDispenserView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: SimDispenserPresenterImpl): SimDispenserPresenter
}
