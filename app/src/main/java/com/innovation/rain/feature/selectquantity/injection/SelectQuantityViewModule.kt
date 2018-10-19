package com.innovation.rain.feature.selectquantity.injection

import com.innovation.rain.feature.selectquantity.presenter.SelectQuantityPresenter
import com.innovation.rain.feature.selectquantity.presenter.SelectQuantityPresenterImpl
import com.innovation.rain.feature.selectquantity.view.SelectQuantityFragment
import com.innovation.rain.feature.selectquantity.view.SelectQuantityView
import com.sf0404.core.application.scope.PerView
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
