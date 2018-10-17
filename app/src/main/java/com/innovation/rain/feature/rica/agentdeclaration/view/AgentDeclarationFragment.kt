package  com.innovation.rain.feature.rica.agentdeclaration.view

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.home.presenter.RicaHomePresenter
import kotlinx.android.synthetic.main.fragment_rica_verify_loaded.*


class AgentDeclarationFragment() : BaseRicaFragment<RicaHomePresenter>(){

    /*@Inject
    lateinit var presenter: RicaHomePresenter*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ricaVerifyFragment.setOnClickListener {
            activity?.finish()
        }

    }

    override fun getPresenter(): RicaHomePresenter? {
        return null
    }

    override fun getLayoutId() = R.layout.fragment_rica_verify_loaded


    override fun onRicaStatePreLoad() {
    }

    override fun onRicaStateLoaded() {
    }

    override fun onRicaStateDone() {
    }

    override fun onProceedButtonClicked() {
    }


}
