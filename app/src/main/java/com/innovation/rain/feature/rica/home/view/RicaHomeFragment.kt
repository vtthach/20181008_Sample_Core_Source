package com.innovation.rain.feature.rica.home.view

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.enums.RicaState
import com.innovation.rain.feature.rica.agentverification.view.AgentVerificationFragment
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.home.presenter.RicaHomePresenter
import com.innovation.rain.feature.rica.poa.view.ProofOfAddressFragment
import com.innovation.rain.feature.rica.sample.SampleFragment
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.fragment_rica_home.*
import java.util.*
import javax.inject.Inject


class RicaHomeFragment : BasePresenterInjectionFragment<RicaHomePresenter>(), RicaHomeView {

    companion object {
        const val BUNDLE_KEY_RICA_STATE = "BUNDLE_KEY_RICA_STATE"
        const val REQUEST_CODE = 1001
    }

    @Inject
    lateinit var viewPresenter: RicaHomePresenter

    private lateinit var mFragments: List<BaseRicaFragment<*>>

    override fun getPresenter() = viewPresenter

    override fun getLayoutId() = R.layout.fragment_rica_home

    override fun enableButtonProceed(allowEnableProceedButton: Boolean) {
        btnProceed.isEnabled = allowEnableProceedButton
        btnProceed.isActivated = allowEnableProceedButton
    }

    override fun notifyRicaStateDone() {
        val index = getCurrentIndex()
        mFragments[index].ricaState = RicaState.STATE_DONE
        if (index < mFragments.size - 1) {
            mFragments[index + 1].ricaState = RicaState.STATE_LOADED
        }
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
        val transaction = childFragmentManager.beginTransaction().apply {
            add(R.id.container_sa_id, mFragments[0])
            add(R.id.container_sa_por, mFragments[1])
            add(R.id.container_sa_rica, mFragments[2])
            commitAllowingStateLoss()
        }

    }

    private fun getCurrentIndex(): Int {
        for (i in mFragments.indices) {
            if (mFragments[i].ricaState == RicaState.STATE_LOADED) {
                return i
            }
        }
        return -1
    }

    /**
     * fragment list
     */
    private fun getFragments(): List<BaseRicaFragment<*>> {
        val firstFragment: BaseRicaFragment<*> = SampleFragment()
        var ricaVerifyFragment :  BaseRicaFragment<*> = AgentVerificationFragment()
        val b = Bundle()
        b.putInt(BUNDLE_KEY_RICA_STATE, RicaState.STATE_LOADED.id)
        firstFragment.arguments = b
        return Arrays.asList<BaseRicaFragment<*>>(firstFragment, ProofOfAddressFragment(), SampleFragment())
        return Arrays.asList<BaseRicaFragment<*>>(firstFragment, SampleFragment(), ricaVerifyFragment)
    }
}
