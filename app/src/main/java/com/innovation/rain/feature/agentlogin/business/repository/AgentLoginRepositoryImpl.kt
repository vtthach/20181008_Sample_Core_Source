package com.innovation.rain.feature.agentlogin.business.repository

import com.innovation.rain.feature.agentlogin.business.model.AgentLoginRequest
import com.innovation.rain.feature.agentlogin.business.service.AgentLoginService
import javax.inject.Inject

class AgentLoginRepositoryImpl @Inject
constructor(private val service: AgentLoginService
) : AgentLoginRepository {

    override fun agentLogin(request: AgentLoginRequest) = service.agentLogin(request)
}