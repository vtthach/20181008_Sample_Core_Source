package com.innovation.rain.feature.collection.orders.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.collection.orders.presenter.OrderListPresenter
import com.innovation.rain.feature.collection.orders.presenter.OrderListPresenterImpl
import com.innovation.rain.feature.collection.orders.view.OrderListFragment
import com.innovation.rain.feature.collection.orders.view.OrderListView
import dagger.Binds
import dagger.Module

@Module
abstract class OrderListViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: OrderListFragment): OrderListView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: OrderListPresenterImpl): OrderListPresenter
}