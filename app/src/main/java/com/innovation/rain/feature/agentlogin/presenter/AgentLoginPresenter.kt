package com.innovation.rain.feature.agentlogin.presenter

import com.sf0404.core.application.base.presenter.BasePresenter


interface AgentLoginPresenter : BasePresenter {
    fun onTextPasswordChanged(s: String)

    fun onTextUserIdChanged(s: String)

    fun login()
}