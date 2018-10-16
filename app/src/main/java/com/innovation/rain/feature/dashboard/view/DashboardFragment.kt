package com.innovation.rain.feature.agentlogin.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.app.enums.RicaState
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.dashboard.base.BaseRicaFragment
import com.innovation.rain.feature.dashboard.presenter.DashboardPresenter
import com.innovation.rain.feature.dashboard.view.DashboardView
import com.innovation.rain.feature.welcomemenu.view.WelcomeMenuFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.*
import javax.inject.Inject


class DashboardFragment : BasePresenterInjectionFragment<DashboardPresenter>(), DashboardView {

    private val REQUEST_CODE = 1001
    public val BUNDLE_KEY_RICA_STATE = "BUNDLE_KEY_RICA_STATE"

    @Inject
    lateinit var viewPresenter: DashboardPresenter

    lateinit var mFragments: List<BaseRicaFragment<*>>

    override fun getPresenter() = viewPresenter

    override fun getLayoutId() = R.layout.fragment_dashboard

    override fun enableButtonProceed(allowEnableProceedButton: Boolean) {
        btnProceed.isEnabled = allowEnableProceedButton
        btnProceed.isActivated = allowEnableProceedButton
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnExit.setOnClickListener {
            activity?.finish()
        }
        btnProceed.setOnClickListener {
            // handle logic for current fragment
            mFragments[getCurrentIndex()].onProceedButtonClicked()
        }
        initView()
    }

    private fun initView() {
        mFragments = getFragments()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.add(R.id.container_sa_id, mFragments[0])
        transaction?.add(R.id.container_sa_por, mFragments[1])
        transaction?.add(R.id.container_sa_rica, mFragments[2])
        transaction?.commitAllowingStateLoss()
        // load first fragment by default
        mFragments[0].ricaState = RicaState.STATE_LOADED
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (REQUEST_CODE == requestCode && resultCode == RESULT_OK) {
            val state: RicaState = RicaState.valueOf(data?.extras?.getInt(BUNDLE_KEY_RICA_STATE) ?: -1)
            if (state == RicaState.STATE_DONE) {
                val index = getCurrentIndex()
                mFragments[index].ricaState = RicaState.STATE_DONE
                if (index < mFragments.size - 1) {
                    mFragments[index + 1].ricaState = RicaState.STATE_LOADED
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun getCurrentIndex(): Int {
        for (i in mFragments.indices) {
            if (mFragments[i].ricaState === RicaState.STATE_LOADED) {
                return i
            }
        }
        return 0
    }

    private fun getFragments(): List<BaseRicaFragment<*>> {
        return Arrays.asList<BaseRicaFragment<*>>(SampleFragment(), SampleFragment(), SampleFragment())
    }
}
