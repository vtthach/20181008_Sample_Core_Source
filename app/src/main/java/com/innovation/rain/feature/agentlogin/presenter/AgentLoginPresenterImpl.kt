package com.innovation.rain.feature.agentlogin.presenter

import com.innovation.rain.app.injection.module.model.AppBus
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginCallback
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginParam
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginUiModel
import com.innovation.rain.feature.agentlogin.business.usecase.AgentLoginUseCase
import com.innovation.rain.feature.agentlogin.view.AgentLoginView
import com.sf0404.common.utils.ValidationUtils
import com.sf0404.core.application.base.presenter.BasePresenterImpl
import timber.log.Timber
import javax.inject.Inject


class AgentLoginPresenterImpl @Inject
constructor(view: AgentLoginView,
            private val useCase: AgentLoginUseCase,
            private val appBus: AppBus
) : BasePresenterImpl<AgentLoginView>(view), AgentLoginPresenter {

    private var pw: String? = null
    private var userId: String? = null

    private val isAllowEnableSignInButton: Boolean
        get() {
            if (ValidationUtils.isNullOrEmpty(pw)) {
                return false
            }
            return !ValidationUtils.isNullOrEmpty(userId)
        }

    override fun onTextPasswordChanged(s: String) {
        pw = s
        startCheckEnableSignInButton()
    }

    private fun startCheckEnableSignInButton() {
        view.enableButtonSignIn(isAllowEnableSignInButton)
    }

    override fun onTextUserIdChanged(s: String) {
        this.userId = s
        startCheckEnableSignInButton()
    }

    override fun login() {
        addDisposable(useCase.setCallback(object : AgentLoginCallback(view) {
            override fun onMaxAttemptsError(code: String) {
                view.showMaxAttemptsError()
            }

            override fun onAgentNotFound(code: String) {
                view.showAgentNotFoundError()
            }

            override fun onAlreadyLogin(code: String) {
                view.showAlreadyLoginError()
            }

            override fun onSuccess(info: AgentLoginUiModel) {
                Timber.i("sessionId: ${info.recordId}")
                appBus.sessionId = info.recordId
                view.gotoWelcomeMenuScreen()
            }
        }).execute(AgentLoginParam(userId, pw)))
    }
}
