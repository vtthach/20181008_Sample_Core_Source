package com.innovation.rain.feature.agentlogin.business.mapper

import com.innovation.rain.feature.agentlogin.business.model.AgentLoginParam
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginRequest
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginResponse
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginUiModel
import javax.inject.Inject

class AgentLoginMapperImpl @Inject constructor() : AgentLoginMapper {

    override fun getUiModelFromResponse(r: AgentLoginResponse?): AgentLoginUiModel {
        return AgentLoginUiModel(r?.recordId ?: "", r?.success ?: false)
    }

    override fun getRequestFromParam(p: AgentLoginParam?): AgentLoginRequest {
        return AgentLoginRequest(p?.username ?: "", p?.password ?: "")
    }
}
