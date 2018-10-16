package com.innovation.rain.feature.rica.dashboard.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.agentlogin.view.DashboardFragment
import com.innovation.rain.feature.rica.dashboard.presenter.DashboardPresenter
import com.innovation.rain.feature.rica.dashboard.presenter.DashboardPresenterImpl
import com.innovation.rain.feature.rica.dashboard.view.DashboardView

import dagger.Binds
import dagger.Module

@Module
abstract class SampleViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: DashboardFragment): DashboardView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: DashboardPresenterImpl): DashboardPresenter
}
