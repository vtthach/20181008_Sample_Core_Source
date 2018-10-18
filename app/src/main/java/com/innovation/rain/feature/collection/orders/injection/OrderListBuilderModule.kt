package com.innovation.rain.feature.collection.orders.injection

import com.innovation.rain.feature.collection.orders.view.OrderListFragment
import com.sf0404.core.application.scope.PerView

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class OrderListBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = arrayOf(OrderListViewModule::class))
    internal abstract fun contributeFragment(): OrderListFragment
}