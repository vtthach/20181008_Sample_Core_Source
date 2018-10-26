package com.innovation.rain.feature.agentlogin.business.model

import com.sf0404.core.application.business.usecase.BaseParam

class AgentLoginParam(val username: String?,
                      val password: String?
) : BaseParam()