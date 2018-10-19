package com.innovation.rain.feature.selectquantity.injection

import com.innovation.rain.feature.selectquantity.view.SelectQuantityFragment
import com.sf0404.core.application.scope.PerView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SelectQuantityBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = arrayOf(SelectQuantityViewModule::class))
    internal abstract fun contributeFragment(): SelectQuantityFragment
}