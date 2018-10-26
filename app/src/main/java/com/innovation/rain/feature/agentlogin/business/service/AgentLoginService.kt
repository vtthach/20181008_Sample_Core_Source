package com.innovation.rain.feature.agentlogin.business.service

import com.innovation.rain.app.constant.ApiConstants
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginRequest
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface AgentLoginService {

    @POST(ApiConstants.Url.AGENT_LOGIN)
    fun agentLogin(@Body request: AgentLoginRequest): Observable<AgentLoginResponse>
}