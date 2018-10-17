package com.innovation.rain.feature.rica.home.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.rica.home.view.RicaHomeFragment
import com.innovation.rain.feature.rica.home.presenter.RicaHomePresenter
import com.innovation.rain.feature.rica.home.presenter.RicaHomePresenterImpl
import com.innovation.rain.feature.rica.home.view.RicaHomeView

import dagger.Binds
import dagger.Module

@Module
abstract class SampleViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: RicaHomeFragment): RicaHomeView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: RicaHomePresenterImpl): RicaHomePresenter
}
