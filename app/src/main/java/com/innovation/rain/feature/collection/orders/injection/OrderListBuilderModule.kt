package com.innovation.rain.feature.collection.orders.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.collection.orders.view.OrderListFragment

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