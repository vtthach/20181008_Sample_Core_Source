package com.innovation.rain.feature.rica.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewAnimator
import com.innovation.rain.R
import com.innovation.rain.app.enums.RicaState
import com.innovation.rain.feature.rica.home.callback.RicaStateView
import com.innovation.rain.feature.rica.home.view.RicaHomeFragment
import com.innovation.rain.feature.rica.home.view.RicaHomeView
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import com.sf0404.core.application.base.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_rica.*

abstract class BaseRicaFragment<T : BasePresenter> : BasePresenterInjectionFragment<T>(), RicaStateView {
    override fun showPreLoadState() {
        (view as? ViewAnimator)?.displayedChild = 0
    }

    override fun showLoadedState() {
        (view as? ViewAnimator)?.displayedChild = 1
        enableButtonProceed(false)
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val loadedStateView = layoutInflater.inflate(getLoadedStateLayout(), null)
        val loadedStateLayout = view?.findViewById(R.id.loadedState) as ViewGroup
        loadedStateLayout.addView(loadedStateView)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stateId = arguments?.getInt(RicaHomeFragment.BUNDLE_KEY_RICA_STATE, RicaState.STATE_PRE_LOADED.id)
        ricaState = RicaState.valueOf(stateId ?: RicaState.STATE_PRE_LOADED.id)

        preloadTitleTv.text = getPreLoadStateTitle()
        doneTitleTv.text = getDoneStateTitle()
        showPreLoadState()
    }

    protected fun enableButtonProceed(allowEnableProceedButton: Boolean) {
        (parentFragment as? RicaHomeView)?.enableButtonProceed(allowEnableProceedButton)
    }

    protected fun notifyRicaStateDone() {
        val f = parentFragment as RicaHomeView?
        f?.notifyRicaStateDone()
    }

    abstract fun getPreLoadStateTitle(): String
    abstract fun getDoneStateTitle(): String
    abstract fun getLoadedStateLayout(): Int
}
