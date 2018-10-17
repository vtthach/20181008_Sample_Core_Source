package  com.innovation.rain.feature.rica.agentverification.view

import com.innovation.rain.app.base.presenter.BasePresenterView

interface AgentVerificationView : BasePresenterView {
    fun enableButtonProceed(allowEnableProceedButton: Boolean)
}
