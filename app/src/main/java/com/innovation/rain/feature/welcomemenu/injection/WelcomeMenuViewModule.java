package com.innovation.rain.feature.welcomemenu.injection;

import com.sf0404.core.application.scope.PerView;
import com.innovation.rain.feature.welcomemenu.presenter.WelcomeMenuPresenter;
import com.innovation.rain.feature.welcomemenu.presenter.WelcomeMenuPresenterImpl;
import com.innovation.rain.feature.welcomemenu.view.WelcomeMenuFragment;
import com.innovation.rain.feature.welcomemenu.view.WelcomeMenuView;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WelcomeMenuViewModule {

    @PerView
    @Binds
    abstract WelcomeMenuView provideView(WelcomeMenuFragment view);

    @PerView
    @Binds
    abstract WelcomeMenuPresenter providePresenter(WelcomeMenuPresenterImpl presenter);
}
