package com.innovation.rain.feature.rica.home.view

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.app.enums.RicaState
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.home.presenter.RicaHomePresenter
import com.innovation.rain.feature.rica.sample.SampleFragment
import com.innovation.rain.feature.rica.scaniddoc.home.view.ScanIdDocFragment
import com.sf0404.common.container.mode.ToolbarMode
import kotlinx.android.synthetic.main.fragment_rica_home.*
import java.util.*
import javax.inject.Inject


class RicaHomeFragment : BasePresenterInjectionFragment<RicaHomePresenter>(), RicaHomeView {

    companion object {
        const val BUNDLE_KEY_RICA_STATE = "BUNDLE_KEY_RICA_STATE"
        const val REQUEST_CODE = 1001
        const val KEY_DONE_STEP = "KEY_DONE_STEP"
        const val DEFAULT = -1
        const val DONE_STEP_1 = 0
        const val DONE_STEP_2 = 1
        const val DONE_STEP_3 = 2

        fun showMe(activity: FragmentActivity?, stepDone: Int) {
            activity?.showFragment<RicaHomeFragment>(ToolbarMode.NONE,
                    Bundle().apply { putInt(RicaHomeFragment.KEY_DONE_STEP, stepDone) })
        }
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
            activity?.finishAffinity()
        }
        btnProceed.setOnClickListener {
            // handle logic for current fragment
            mFragments[getCurrentIndex()].onProceedButtonClicked()
        }
        initView()
    }

    private fun initView() {
        mFragments = getFragments()
        arguments?.run {
            val stepDone = getInt(KEY_DONE_STEP, DEFAULT)
            if (stepDone != DEFAULT) {
                val fragment = mFragments[stepDone]
                fragment.arguments = Bundle().apply { putInt(BUNDLE_KEY_RICA_STATE, RicaState.STATE_DONE.id) }
            }
        }

        val transaction = fragmentManager?.beginTransaction()
        transaction?.add(R.id.container_sa_id, mFragments[0])
        transaction?.add(R.id.container_sa_por, mFragments[1])
        transaction?.add(R.id.container_sa_rica, mFragments[2])
        transaction?.commitAllowingStateLoss()
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
        return Arrays.asList<BaseRicaFragment<*>>(ScanIdDocFragment(), SampleFragment(), SampleFragment())
    }
}
