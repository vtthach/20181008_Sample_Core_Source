package com.innovation.rain.feature.collection.simdispenser.injection;

import com.innovation.rain.feature.collection.signin.view.DispenseFragment;
import com.innovation.rain.feature.collection.simdispenser.presenter.DispensePresenter;
import com.innovation.rain.feature.collection.simdispenser.presenter.DispensePresenterImpl;
import com.innovation.rain.feature.collection.simdispenser.view.DispenseView;
import com.sf0404.core.application.scope.PerView;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DispenseViewModule {

    @PerView
    @Binds
    abstract DispenseView provideView(DispenseFragment view);

    @PerView
    @Binds
    abstract DispensePresenter providePresenter(DispensePresenterImpl presenter);
}
