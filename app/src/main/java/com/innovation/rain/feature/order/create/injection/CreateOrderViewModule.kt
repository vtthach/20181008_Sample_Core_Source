package com.innovation.rain.feature.order.create.injection

import com.innovation.rain.feature.order.create.presenter.CreateOrderPresenter
import com.innovation.rain.feature.order.create.presenter.CreateOrderPresenterImpl
import com.innovation.rain.feature.order.create.view.CreateOrderFragment
import com.innovation.rain.feature.order.create.view.CreateOrderView
import com.sf0404.core.application.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class CreateOrderViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: CreateOrderFragment): CreateOrderView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: CreateOrderPresenterImpl): CreateOrderPresenter
}
