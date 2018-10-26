package com.innovation.rain.feature.agentlogin.business.model

import com.innovation.rain.feature.agentlogin.view.AgentLoginView
import com.sf0404.core.application.business.core.callback.BaseCallbackImpl

abstract class AgentLoginCallback(view: AgentLoginView) : BaseCallbackImpl<AgentLoginView, AgentLoginUiModel>(view) {
    abstract fun onAgentNotFound(code: String)
    abstract fun onMaxAttemptsError(code: String)
    abstract fun onAlreadyLogin(code: String)
}