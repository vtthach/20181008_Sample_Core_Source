package com.innovation.rain.feature.collection.signin.view

import android.os.Bundle
import android.view.View
import android.widget.ViewAnimator
import com.innovation.rain.R
import com.innovation.rain.app.utils.showExitDialog
import com.innovation.rain.feature.collection.signin.view.exception.SimDispenserErrorFragment
import com.innovation.rain.feature.collection.simdispenser.presenter.SimDispenserPresenter
import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserView
import com.rain.carddispenser.model.SimEntity
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.fragment_collection_dispensing.*
import javax.inject.Inject


class SimDispenserFragment : BasePresenterInjectionFragment<SimDispenserPresenter>(), SimDispenserView {

    @Inject
    lateinit var viewPresenter: SimDispenserPresenter

    override fun getPresenter() = viewPresenter

    override fun showViewDispensing() {
        progress.startAnim()
        progress.visibility = View.VISIBLE
        (view as? ViewAnimator)?.displayedChild = 0
    }

    override fun showDispensingSuccess(simEntity: SimEntity) {
        progress.stopAnim()
        progress.visibility = View.INVISIBLE
        (view as? ViewAnimator)?.displayedChild = 1
        txtIccId.text = getString(R.string.sim_iccid) + simEntity.iccid
    }

    override fun showDialogDispensingFail(apiCode: String) {
        progress.stopAnim()
        progress.visibility = View.INVISIBLE
        SimDispenserErrorFragment.newInstance(apiCode).show(fragmentManager, SimDispenserErrorFragment::class.java.simpleName)
    }

    override fun enableScanAnotherSim(enable: Boolean) {
        btnScanAnotherSim.isEnabled = enable
        btnScanAnotherSim.isActivated = enable
    }

    override fun getLayoutId() = R.layout.fragment_collection_dispensing

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnScanAnotherSim.isActivated = true

        btnExit.setOnClickListener {
            fragmentManager?.showExitDialog()
        }

        btnScanAnotherSim.setOnClickListener {
            viewPresenter.scanAnotherSim()
        }
    }

    override fun toggleProgress(isShow: Boolean) {
    }
}
