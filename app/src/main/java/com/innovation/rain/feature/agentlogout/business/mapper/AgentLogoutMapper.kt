package com.innovation.rain.feature.agentlogout.business.mapper

import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutParam
import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutRequest
import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutResponse
import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutUiModel
import com.sf0404.core.application.business.mapper.BaseMapper

interface AgentLogoutMapper : BaseMapper<AgentLogoutUiModel, AgentLogoutParam, AgentLogoutRequest, AgentLogoutResponse> {
    fun getHeaderFromParam(param: AgentLogoutParam) : HashMap<String, String>
}