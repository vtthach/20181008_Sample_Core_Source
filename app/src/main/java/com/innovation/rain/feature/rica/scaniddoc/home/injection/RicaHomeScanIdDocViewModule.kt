package com.innovation.rain.feature.rica.scaniddoc.home.injection

import com.innovation.rain.feature.rica.scaniddoc.home.presenter.RicaHomeScanIdDocPresenter
import com.innovation.rain.feature.rica.scaniddoc.home.presenter.RicaHomeScanIdDocPresenterImpl
import com.innovation.rain.feature.rica.scaniddoc.home.view.RicaHomeScanIdDocFragment
import com.innovation.rain.feature.rica.scaniddoc.home.view.RicaHomeScanIdDocView
import com.sf0404.core.application.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class RicaHomeScanIdDocViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: RicaHomeScanIdDocFragment): RicaHomeScanIdDocView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: RicaHomeScanIdDocPresenterImpl): RicaHomeScanIdDocPresenter
}
