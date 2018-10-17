package com.innovation.rain.feature.rica.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ViewAnimator
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.app.base.presenter.BasePresenter
import com.innovation.rain.app.enums.RicaState
import com.innovation.rain.feature.rica.home.callback.RicaStateView
import com.innovation.rain.feature.rica.home.view.RicaHomeFragment
import com.innovation.rain.feature.rica.home.view.RicaHomeView
import kotlinx.android.synthetic.main.fragment_rica.*

abstract class BaseRicaFragment<T : BasePresenter> : BasePresenterInjectionFragment<T>(), RicaStateView {
    override fun showPreLoadState() {
        (view as? ViewAnimator)?.displayedChild = 0
    }

    override fun showLoadedState() {
        (view as? ViewAnimator)?.displayedChild = 1
    }

    override fun showDoneState() {
        (view as? ViewAnimator)?.displayedChild = 2
    }

    var ricaState: RicaState? = null
        set(ricaState) {
            field = ricaState
            when (ricaState) {
                RicaState.STATE_PRE_LOADED -> showPreLoadState()
                RicaState.STATE_LOADED -> showLoadedState()
                RicaState.STATE_DONE -> showDoneState()
                else -> {
                }
            }
        }

    override fun getLayoutId(): Int {
        return R.layout.fragment_rica
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val state: RicaState = RicaState.valueOf(arguments?.getInt(RicaHomeFragment.BUNDLE_KEY_RICA_STATE, -1)
                ?: -1)
        ricaState = if (state != RicaState.UNKNOWN) {
            state
        } else {
            RicaState.STATE_PRE_LOADED
        }

        preloadTitleTv.text = getPreLoadStateTitle()
        doneTitleTv.text = getDoneStateTitle()
        val loadedStateView = layoutInflater.inflate(getLoadedStateLayout(), null)
        loadedState.addView(loadedStateView)
        showPreLoadState()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (RicaHomeFragment.REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK) {
            val state: RicaState = RicaState.valueOf(data?.extras?.getInt(RicaHomeFragment.BUNDLE_KEY_RICA_STATE)
                    ?: -1)
            if (state == RicaState.STATE_DONE) {
                notifyRicaStateDone()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    protected fun enableButtonProceed(allowEnableProceedButton: Boolean) {
        val f = parentFragment as RicaHomeView?
        f?.enableButtonProceed(allowEnableProceedButton)
    }

    protected fun notifyRicaStateDone() {
        val f = parentFragment as RicaHomeView?
        f?.notifyRicaStateDone()
    }

    abstract fun getPreLoadStateTitle(): String
    abstract fun getDoneStateTitle(): String
    abstract fun getLoadedStateLayout(): Int
}
