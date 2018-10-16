package com.innovation.rain.feature.agentdeclaration.presenter

import com.innovation.rain.app.base.presenter.BasePresenterImpl

import com.innovation.rain.feature.agentdeclaration.view.AgentDeclarationView

import javax.inject.Inject


class AgentDeclarationPresenterImpl @Inject
constructor(view: AgentDeclarationView) : BasePresenterImpl<AgentDeclarationView>(view), AgentDeclarationPresenter {


}
