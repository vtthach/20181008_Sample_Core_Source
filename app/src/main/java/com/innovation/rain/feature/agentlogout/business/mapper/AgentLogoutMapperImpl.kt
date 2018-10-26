package com.innovation.rain.feature.agentlogout.business.mapper

import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutParam
import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutRequest
import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutResponse
import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutUiModel
import javax.inject.Inject

class AgentLogoutMapperImpl @Inject constructor() : AgentLogoutMapper {

    override fun getUiModelFromResponse(r: AgentLogoutResponse?): AgentLogoutUiModel {
        return AgentLogoutUiModel()
    }

    override fun getRequestFromParam(p: AgentLogoutParam?): AgentLogoutRequest {
        return AgentLogoutRequest()
    }

    override fun getHeaderFromParam(param: AgentLogoutParam): HashMap<String, String> {
        return param.header
    }
}
