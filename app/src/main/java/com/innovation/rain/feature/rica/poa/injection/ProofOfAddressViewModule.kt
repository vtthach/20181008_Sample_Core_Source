package com.innovation.rain.feature.rica.poa.injection

import com.innovation.rain.feature.rica.poa.presenter.ProofOfAddressPresenter
import com.innovation.rain.feature.rica.poa.presenter.ProofOfAddressPresenterImpl
import com.innovation.rain.feature.rica.poa.view.ProofOfAddressFragment
import com.innovation.rain.feature.rica.poa.view.ProofOfAddressView

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
