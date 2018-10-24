package com.innovation.rain.feature.agentlogin.view


import com.sf0404.core.application.base.presenter.BaseView

interface AgentLoginView : BaseView {
    fun enableButtonSignIn(allowEnableSignInButton: Boolean)

    fun gotoWelcomeMenuScreen()

    fun showAlreadyLoginError()

    fun showAgentNotFoundError()
}
