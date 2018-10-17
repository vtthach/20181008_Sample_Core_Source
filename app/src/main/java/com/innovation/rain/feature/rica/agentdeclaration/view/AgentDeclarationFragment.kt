package  com.innovation.rain.feature.rica.agentdeclaration.view

import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.feature.rica.agentdeclaration.presenter.AgentDeclarationPresenter
import com.innovation.rain.R
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.home.presenter.RicaHomePresenter
import javax.inject.Inject


class AgentDeclarationFragment : BaseRicaFragment<RicaHomePresenter>(), AgentDeclarationView {

    /*@Inject
    lateinit var presenter: RicaHomePresenter*/

    override fun getPresenter(): RicaHomePresenter? {
        return null
    }

    override fun getLayoutId() = R.layout.fragment_rica_agent_declaration


    override fun onRicaStatePreLoad() {
    }

    override fun onRicaStateLoaded() {
    }

    override fun onRicaStateDone() {
    }

    override fun onProceedButtonClicked() {
    }

    override fun enableButtonProceed(allowEnableProceedButton: Boolean){

    }

}
