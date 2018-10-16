package com.innovation.rain.feature.agentlogin.view

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.app.enums.RicaState
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.home.presenter.RicaHomePresenter
import com.innovation.rain.feature.rica.home.view.RicaHomeView
import kotlinx.android.synthetic.main.fragment_rica_home.*
import java.util.*
import javax.inject.Inject


class RicaHomeFragment : BasePresenterInjectionFragment<RicaHomePresenter>(), RicaHomeView {

    private val REQUEST_CODE = 1001

    @Inject
    lateinit var viewPresenter: RicaHomePresenter

    lateinit var mFragments: List<BaseRicaFragment<*>>

    override fun getPresenter() = viewPresenter

    override fun getLayoutId() = R.layout.fragment_rica_home

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
            if (mFragments[i].ricaState == RicaState.STATE_LOADED) {
                return i
            }
        }
        return 0
    }

    /**
     * fragment list
     */
    private fun getFragments(): List<BaseRicaFragment<*>> {
        val firstFragment : BaseRicaFragment<*> = SampleFragment()
        val b = Bundle()
        b.putInt(BUNDLE_KEY_RICA_STATE, RicaState.STATE_LOADED.id)
        firstFragment.arguments = b
        return Arrays.asList<BaseRicaFragment<*>>(firstFragment, SampleFragment(), SampleFragment())
    }

    companion object {
        const val BUNDLE_KEY_RICA_STATE = "BUNDLE_KEY_RICA_STATE"
    }
}
