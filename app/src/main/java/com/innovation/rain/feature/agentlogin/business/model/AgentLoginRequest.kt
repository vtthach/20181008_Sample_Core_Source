package com.innovation.rain.feature.agentlogin.business.model

import com.google.gson.annotations.SerializedName
import com.sf0404.core.application.business.model.BaseRequest

class AgentLoginRequest(@SerializedName("username") val username: String,
                        @SerializedName("password") val password: String
) : BaseRequest()