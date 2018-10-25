package com.innovation.rain.feature.agentlogin.business.usecase

import com.innovation.rain.app.constant.ApiConstants
import com.innovation.rain.app.constant.Constants
import com.innovation.rain.feature.agentlogin.business.mapper.AgentLoginMapper
import com.innovation.rain.feature.agentlogin.business.model.*
import com.innovation.rain.feature.agentlogin.business.repository.AgentLoginRepository
import com.sf0404.core.application.business.core.exception.BusinessErrorException
import com.sf0404.core.application.business.usecase.UseCaseImpl
import javax.inject.Inject

class AgentLoginUseCaseImpl @Inject
constructor(private val repository: AgentLoginRepository,
            mapper: AgentLoginMapper
) : UseCaseImpl<AgentLoginUiModel, AgentLoginParam, AgentLoginRequest, AgentLoginResponse, AgentLoginCallback>(mapper), AgentLoginUseCase {

    private var counter = 0

    override fun getRepositoryObservable(requestFromParam: AgentLoginRequest) = repository.agentLogin(requestFromParam)

    override fun handleBusiness(e: BusinessErrorException): Boolean {
        return e.handle()
    }

    private fun BusinessErrorException.handle(): Boolean {
        val code = this.code ?: return false
        return when (code) {
            ApiConstants.ErrorCode.ALREADY_LOGIN -> {
                callback.onAlreadyLogin(code)
                true
            }
            ApiConstants.ErrorCode.AGENT_NOT_FOUND -> {
                counter++
                if (counter < Constants.MAX_ATTEMPTS) {
                    callback.onAgentNotFound(code)
                } else {
                    callback.onMaxAttemptsError(code)
                    counter = 0
                }
                true
            }
            else -> false
        }
    }
}