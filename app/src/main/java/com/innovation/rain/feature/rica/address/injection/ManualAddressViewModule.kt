package com.innovation.rain.feature.rica.address.injection

import com.innovation.rain.feature.rica.address.presenter.ManualAddressPresenter
import com.innovation.rain.feature.rica.address.presenter.ManualAddressPresenterImpl
import com.innovation.rain.feature.rica.address.view.ManualAddressFragment
import com.innovation.rain.feature.rica.address.view.ManualAddressView

import com.innovation.rain.app.injection.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class ManualAddressViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: ManualAddressFragment): ManualAddressView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: ManualAddressPresenterImpl): ManualAddressPresenter
}
