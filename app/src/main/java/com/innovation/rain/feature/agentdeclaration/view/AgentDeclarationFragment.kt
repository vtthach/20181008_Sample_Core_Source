package  com.innovation.rain.feature.agentdeclaration.view

import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.feature.agentdeclaration.presenter.AgentDeclarationPresenter
import com.innovation.rain.R
import javax.inject.Inject


class AgentDeclarationFragment : BasePresenterInjectionFragment<AgentDeclarationPresenter>(), AgentDeclarationView {

    @Inject
    lateinit var viewPresenter: AgentDeclarationPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_rica_agent_declaration
        //TODO: provide layout id
    }

}
