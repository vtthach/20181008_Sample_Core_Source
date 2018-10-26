package com.innovation.rain.feature.account.create.injection

import com.innovation.rain.feature.account.create.view.CreateAccountFragment
import com.sf0404.core.application.scope.PerView
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CreateAccountBuilderModule {
    @PerView
    @ContributesAndroidInjector(modules = arrayOf(CreateAccountViewModule::class))
    internal abstract fun contributeFragment(): CreateAccountFragment
}