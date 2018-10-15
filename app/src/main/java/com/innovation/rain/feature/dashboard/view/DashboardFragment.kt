package com.innovation.rain.feature.agentlogin.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.dashboard.presenter.DashboardPresenter
import com.innovation.rain.feature.dashboard.view.DashboardView
import com.innovation.rain.feature.welcomemenu.view.WelcomeMenuFragment
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject


class DashboardFragment : BasePresenterInjectionFragment<DashboardPresenter>(), DashboardView {

    @Inject
    lateinit var viewPresenter: DashboardPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId() = R.layout.fragment_dashboard

    override fun enableButtonProceed(allowEnableSignInButton: Boolean) {
        btnProceed.isEnabled = allowEnableSignInButton
        btnProceed.isActivated = allowEnableSignInButton
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnExit.setOnClickListener {
            activity?.finish()
        }
        btnProceed.setOnClickListener {
            activity?.showFragment<WelcomeMenuFragment>()
        }
        initView()
    }

    private fun initView() {
        val fragments = getFragments()
        fragmentManager?.beginTransaction()?.add(R.id.container_sa_id, fragments[0])?.commitAllowingStateLoss()
        fragmentManager?.beginTransaction()?.add(R.id.container_sa_por, fragments[1])?.commitAllowingStateLoss()
        fragmentManager?.beginTransaction()?.add(R.id.container_sa_rica, fragments[2])?.commitAllowingStateLoss()
    }

    private fun getFragments(): List<Fragment> {
        return arrayListOf(WelcomeMenuFragment(), WelcomeMenuFragment(), WelcomeMenuFragment())
    }
}
