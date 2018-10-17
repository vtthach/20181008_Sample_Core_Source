package com.innovation.rain.feature.rica.agentdeclaration.injection

import com.innovation.rain.feature.rica.agentdeclaration.presenter.AgentDeclarationPresenter
import com.innovation.rain.feature.rica.agentdeclaration.presenter.AgentDeclarationPresenterImpl
import com.innovation.rain.feature.rica.agentdeclaration.view.AgentDeclarationFragment
import com.innovation.rain.feature.rica.agentdeclaration.view.AgentDeclarationView

import com.innovation.rain.app.injection.scope.PerView
import dagger.Binds
import dagger.Module

@Module
abstract class AgentDeclarationViewModule {

    @PerView
    @Binds
    internal abstract fun provideView(view: AgentDeclarationFragment): AgentDeclarationView

    @PerView
    @Binds
    internal abstract fun providePresenter(presenter: AgentDeclarationPresenterImpl): AgentDeclarationPresenter
}
