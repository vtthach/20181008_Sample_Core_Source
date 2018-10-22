package com.innovation.rain.feature.rica.poa.home.injection

import com.innovation.rain.feature.rica.poa.home.presenter.ProofOfAddressPresenter
import com.innovation.rain.feature.rica.poa.home.presenter.ProofOfAddressPresenterImpl
import com.innovation.rain.feature.rica.poa.home.view.ProofOfAddressFragment
import com.innovation.rain.feature.rica.poa.home.view.ProofOfAddressView

import com.sf0404.core.application.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class ProofOfAddressViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: ProofOfAddressFragment): ProofOfAddressView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: ProofOfAddressPresenterImpl): ProofOfAddressPresenter
}
