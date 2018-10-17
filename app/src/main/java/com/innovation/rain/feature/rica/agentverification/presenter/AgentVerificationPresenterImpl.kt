package com.innovation.rain.feature.rica.agentverification.presenter

import com.innovation.rain.app.base.presenter.BasePresenterImpl
import com.innovation.rain.feature.rica.agentverification.view.AgentVerificationView
import javax.inject.Inject


class AgentVerificationPresenterImpl @Inject
constructor(view: AgentVerificationView) : BasePresenterImpl<AgentVerificationView>(view), AgentVerificationPresenter {


}
