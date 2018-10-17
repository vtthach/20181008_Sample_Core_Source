package com.innovation.rain.feature.rica.agentdeclaration.injection

import com.innovation.rain.feature.rica.agentdeclaration.presenter.AgentDeclarationPresenter
import com.innovation.rain.feature.rica.agentdeclaration.presenter.AgentDeclarationPresenterImpl
import com.innovation.rain.feature.rica.agentdeclaration.view.AgentDeclarationFragment
import com.innovation.rain.feature.rica.agentdeclaration.view.AgentDeclarationView

import com.innovation.rain.app.injection.scope.PerView
import com.innovation.rain.feature.agentlogin.view.RicaHomeFragment
import com.innovation.rain.feature.rica.home.presenter.RicaHomePresenter
import com.innovation.rain.feature.rica.home.presenter.RicaHomePresenterImpl
import com.innovation.rain.feature.rica.home.view.RicaHomeView
import dagger.Binds
import dagger.Module

@Module
abstract class AgentDeclarationViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: RicaHomeFragment): RicaHomeView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: RicaHomePresenterImpl): RicaHomePresenter
}
