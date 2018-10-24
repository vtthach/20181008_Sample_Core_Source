package com.innovation.rain.feature.agentlogin.business.model

import com.google.gson.annotations.SerializedName
import com.sf0404.core.application.business.model.BaseResponse

class AgentLoginResponse(@SerializedName("recordId") val recordId: String,
                         @SerializedName("errorMessage") val errorMessage: String,
                         @SerializedName("errorCode") val errorCode: Int,
                         @SerializedName("success") val success: Boolean
) : BaseResponse()