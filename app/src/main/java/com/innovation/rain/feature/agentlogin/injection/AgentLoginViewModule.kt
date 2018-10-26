package com.innovation.rain.feature.agentlogin.injection

import com.innovation.rain.feature.agentlogin.presenter.AgentLoginPresenter
import com.innovation.rain.feature.agentlogin.presenter.AgentLoginPresenterImpl
import com.innovation.rain.feature.agentlogin.view.AgentLoginFragment
import com.innovation.rain.feature.agentlogin.view.AgentLoginView
import com.sf0404.core.application.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class AgentLoginViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: AgentLoginFragment): AgentLoginView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: AgentLoginPresenterImpl): AgentLoginPresenter
}
