package com.innovation.rain.feature.dashboard.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.agentlogin.view.DashboardFragment
import com.innovation.rain.feature.dashboard.presenter.DashboardPresenter
import com.innovation.rain.feature.dashboard.presenter.DashboardPresenterImpl
import com.innovation.rain.feature.dashboard.view.DashboardView

import dagger.Binds
import dagger.Module

@Module
abstract class DashboardViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: DashboardFragment): DashboardView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: DashboardPresenterImpl): DashboardPresenter
}
