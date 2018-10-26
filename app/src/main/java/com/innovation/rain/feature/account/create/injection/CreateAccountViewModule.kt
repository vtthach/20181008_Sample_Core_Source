package com.innovation.rain.feature.account.create.injection

import com.innovation.rain.feature.account.create.presenter.CreateAccountPresenter
import com.innovation.rain.feature.account.create.presenter.CreateAccountPresenterImpl
import com.innovation.rain.feature.account.create.view.CreateAccountFragment
import com.innovation.rain.feature.account.create.view.CreateAccountView
import com.sf0404.core.application.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class CreateAccountViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: CreateAccountFragment): CreateAccountView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: CreateAccountPresenterImpl): CreateAccountPresenter
}
