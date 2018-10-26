package com.innovation.rain.feature.agentlogin.business.usecase

import com.innovation.rain.feature.agentlogin.business.model.AgentLoginCallback
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginParam
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginUiModel
import com.sf0404.core.application.business.usecase.UseCase

interface AgentLoginUseCase : UseCase<AgentLoginUiModel, AgentLoginParam, AgentLoginCallback>