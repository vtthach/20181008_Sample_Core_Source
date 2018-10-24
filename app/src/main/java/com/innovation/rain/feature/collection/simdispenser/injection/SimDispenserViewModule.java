package com.innovation.rain.feature.collection.simdispenser.injection;

import com.innovation.rain.feature.collection.signin.view.SimDispenserFragment;
import com.innovation.rain.feature.collection.simdispenser.presenter.SimDispenserPresenter;
import com.innovation.rain.feature.collection.simdispenser.presenter.SimDispenserPresenterImpl;
import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserView;
import com.sf0404.core.application.scope.PerView;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SimDispenserViewModule {

    @PerView
    @Binds
    abstract SimDispenserView provideView(SimDispenserFragment view);

    @PerView
    @Binds
    abstract SimDispenserPresenter providePresenter(SimDispenserPresenterImpl presenter);
}
