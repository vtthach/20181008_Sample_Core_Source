package com.innovation.rain.feature.agentlogout.business.repository

import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutParam
import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutUiModel
import io.reactivex.Observable

interface AgentLogoutRepository {
    fun agentLogout(param: AgentLogoutParam): Observable<AgentLogoutUiModel>
}