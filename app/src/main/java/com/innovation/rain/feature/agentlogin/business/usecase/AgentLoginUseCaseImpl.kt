package com.innovation.rain.feature.agentlogin.business.usecase

import com.innovation.rain.app.constant.ApiConstants
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
                callback.onAgentNotFound(code)
                true
            }
            else -> false
        }
    }
}