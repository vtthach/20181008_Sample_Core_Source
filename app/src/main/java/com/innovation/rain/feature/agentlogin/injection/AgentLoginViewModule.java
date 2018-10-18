package com.innovation.rain.feature.agentlogin.injection;

import com.sf0404.core.application.scope.PerView;
import com.innovation.rain.feature.agentlogin.presenter.AgentLoginPresenter;
import com.innovation.rain.feature.agentlogin.presenter.AgentLoginPresenterImpl;
import com.innovation.rain.feature.agentlogin.view.AgentLoginFragment;
import com.innovation.rain.feature.agentlogin.view.AgentLoginView;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AgentLoginViewModule {

    @PerView
    @Binds
    abstract AgentLoginView provideView(AgentLoginFragment view);

    @PerView
    @Binds
    abstract AgentLoginPresenter providePresenter(AgentLoginPresenterImpl presenter);
}
