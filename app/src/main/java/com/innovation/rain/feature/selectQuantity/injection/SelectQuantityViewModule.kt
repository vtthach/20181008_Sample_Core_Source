package com.innovation.rain.feature.selectQuantity.injection

import com.innovation.rain.feature.selectQuantity.presenter.SelectQuantityPresenter
import com.innovation.rain.feature.selectQuantity.presenter.SelectQuantityPresenterImpl
import com.innovation.rain.feature.selectQuantity.view.SelectQuantityFragment
import com.innovation.rain.feature.selectQuantity.view.SelectQuantityView

import com.innovation.rain.app.injection.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class SelectQuantityViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: SelectQuantityFragment): SelectQuantityView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: SelectQuantityPresenterImpl): SelectQuantityPresenter
}
