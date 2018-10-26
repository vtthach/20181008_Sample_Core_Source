package com.innovation.rain.feature.agentlogin.business.repository

import com.innovation.rain.feature.agentlogin.business.model.AgentLoginRequest
import com.innovation.rain.feature.agentlogin.business.model.AgentLoginResponse
import io.reactivex.Observable
import retrofit2.http.Body

interface AgentLoginRepository {
    fun agentLogin(@Body request: AgentLoginRequest): Observable<AgentLoginResponse>
}