package  com.innovation.rain.feature.rica.agentverification.view

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.enums.RicaState
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.home.presenter.RicaHomePresenter
import kotlinx.android.synthetic.main.fragment_rica_verify_loaded.*


class AgentVerificationFragment() : BaseRicaFragment<RicaHomePresenter>() {

    override fun getPreLoadStateTitle() = getString(R.string.rica_verify_title)

    override fun getDoneStateTitle() = getString(R.string.rica_verify_done_title)

    override fun getLoadedStateLayout(): Int {
        return R.layout.fragment_rica_verify_loaded
    }

    /*@Inject
    lateinit var presenter: RicaHomePresenter*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgCbVerify.setOnClickListener {
            ricaState = RicaState.STATE_DONE
            enableButtonProceed(true)
        }
    }

    override fun getPresenter(): RicaHomePresenter? {
        return null
    }

    override fun onProceedButtonClicked() {
    }

}
