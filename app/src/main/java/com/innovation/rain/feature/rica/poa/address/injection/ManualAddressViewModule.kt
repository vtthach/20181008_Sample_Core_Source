package com.innovation.rain.feature.rica.poa.address.injection

import com.innovation.rain.feature.rica.poa.address.presenter.ManualAddressPresenter
import com.innovation.rain.feature.rica.poa.address.presenter.ManualAddressPresenterImpl
import com.innovation.rain.feature.rica.poa.address.view.ManualAddressFragment
import com.innovation.rain.feature.rica.poa.address.view.ManualAddressView

import com.sf0404.core.application.scope.PerView
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
