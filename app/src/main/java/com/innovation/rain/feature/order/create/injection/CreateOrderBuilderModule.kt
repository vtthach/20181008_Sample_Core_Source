package com.innovation.rain.feature.order.create.injection

import com.innovation.rain.feature.order.create.view.CreateOrderFragment
import com.sf0404.core.application.scope.PerView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CreateOrderBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = arrayOf(CreateOrderViewModule::class))
    internal abstract fun contributeFragment(): CreateOrderFragment
}