package com.innovation.rain.feature.selectQuantity.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.selectQuantity.view.SelectQuantityFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SelectQuantityBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = arrayOf(SelectQuantityViewModule::class))
    internal abstract fun contributeFragment(): SelectQuantityFragment
}