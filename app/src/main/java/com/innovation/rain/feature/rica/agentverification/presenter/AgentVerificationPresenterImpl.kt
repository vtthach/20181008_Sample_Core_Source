package com.innovation.rain.feature.rica.agentverification.presenter

import com.sf0404.core.application.base.presenter.BasePresenterImpl
import com.innovation.rain.feature.rica.agentverification.view.AgentVerificationView
import javax.inject.Inject


class AgentVerificationPresenterImpl @Inject
constructor(view: AgentVerificationView) : BasePresenterImpl<AgentVerificationView>(view), AgentVerificationPresenter {


}
