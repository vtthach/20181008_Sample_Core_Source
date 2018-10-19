package com.innovation.rain.feature.rica.scaniddoc.scan.idcard.injection

import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.presenter.ScanIdCardPresenter
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.presenter.ScanIdCardPresenterImpl
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.view.ScanIdCardFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.view.ScanIdCardView
import com.sf0404.core.application.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class ScanIdCardViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: ScanIdCardFragment): ScanIdCardView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: ScanIdCardPresenterImpl): ScanIdCardPresenter
}
