package com.innovation.rain.feature.collection.signin.injection;

import com.sf0404.core.application.scope.PerView;
import com.innovation.rain.feature.collection.signin.presenter.ClientSignInPresenter;
import com.innovation.rain.feature.collection.signin.presenter.ClientSignInPresenterImpl;
import com.innovation.rain.feature.collection.signin.view.ClientSignInFragment;
import com.innovation.rain.feature.collection.signin.view.ClientSignInView;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ClientSignInViewModule {

    @PerView
    @Binds
    abstract ClientSignInView provideView(ClientSignInFragment view);

    @PerView
    @Binds
    abstract ClientSignInPresenter providePresenter(ClientSignInPresenterImpl presenter);
}
