package com.innovation.rain.feature.agentlogout.business.service

import com.innovation.rain.app.constant.ApiConstants
import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutResponse
import io.reactivex.Observable
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AgentLogoutService {

    @POST(ApiConstants.Url.AGENT_LOGOUT)
    fun agentLogout(@HeaderMap headers: Map<String, String>): Observable<AgentLogoutResponse>
}