package com.innovation.rain.feature.rica.scaniddoc.home.injection

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.rica.scaniddoc.home.presenter.ScanIdDocPresenter
import com.innovation.rain.feature.rica.scaniddoc.home.presenter.ScanIdDocPresenterImpl
import com.innovation.rain.feature.rica.scaniddoc.home.view.ScanIdDocFragment
import com.innovation.rain.feature.rica.scaniddoc.home.view.ScanIdDocView
import dagger.Binds
import dagger.Module

@Module
abstract class ScanIdDocViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: ScanIdDocFragment): ScanIdDocView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: ScanIdDocPresenterImpl): ScanIdDocPresenter
}
