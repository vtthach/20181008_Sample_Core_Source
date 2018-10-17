package com.innovation.rain.feature.rica.base

import android.os.Bundle
import android.view.View

import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.app.base.presenter.BasePresenter
import com.innovation.rain.app.enums.RicaState
import com.innovation.rain.feature.agentlogin.view.RicaHomeFragment
import com.innovation.rain.feature.rica.home.callback.RicaStateView

abstract class BaseRicaFragment<T : BasePresenter> : BasePresenterInjectionFragment<T>(), RicaStateView {

    var ricaState: RicaState? = null
        set(ricaState) {
            field = ricaState
            when (ricaState) {
                RicaState.STATE_PRE_LOADED -> onRicaStatePreLoad()
                RicaState.STATE_LOADED -> onRicaStateLoaded()
                RicaState.STATE_DONE -> onRicaStateDone()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val state: RicaState = RicaState.valueOf(arguments?.getInt(RicaHomeFragment.BUNDLE_KEY_RICA_STATE, -1) ?: -1)
        ricaState = if (state != RicaState.UNKNOWN) {
            state
        } else {
            RicaState.STATE_PRE_LOADED
        }
    }

    protected fun enableButtonProceed(allowEnableProceedButton: Boolean) {
        val f = parentFragment as RicaHomeFragment?
        f?.enableButtonProceed(allowEnableProceedButton)
    }

    protected fun notifyRicaStateDone() {
        val f = parentFragment as RicaHomeFragment?
        f?.notifyRicaStateDone()
    }
}
