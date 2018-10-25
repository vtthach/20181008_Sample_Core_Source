package com.innovation.rain.feature.agentlogout.business.repository

import com.innovation.rain.feature.agentlogout.business.mapper.AgentLogoutMapper
import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutParam
import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutUiModel
import com.innovation.rain.feature.agentlogout.business.service.AgentLogoutService
import io.reactivex.Observable
import javax.inject.Inject

class AgentLogoutRepositoryImpl @Inject
constructor(private val service: AgentLogoutService,
            private val mapper: AgentLogoutMapper
) : AgentLogoutRepository {

    override fun agentLogout(param: AgentLogoutParam): Observable<AgentLogoutUiModel> {
        return service.agentLogout(mapper.getHeaderFromParam(param))
                .map { mapper.getUiModelFromResponse(it) }
    }
}