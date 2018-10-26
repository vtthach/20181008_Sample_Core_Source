package com.innovation.rain.feature.agentlogin.business.mapper

import com.innovation.rain.feature.agentlogin.business.model.AgentLoginParam
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginRequest
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginResponse
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginUiModel
import com.sf0404.core.application.business.mapper.BaseMapper

interface AgentLoginMapper : BaseMapper<AgentLoginUiModel, AgentLoginParam, AgentLoginRequest, AgentLoginResponse>