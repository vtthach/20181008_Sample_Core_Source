package com.innovation.rain.feature.welcomemenu.view

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.util.NavigateUtil
import com.innovation.rain.feature.collection.signin.view.ClientSignInFragment
import com.innovation.rain.feature.welcomemenu.presenter.WelcomeMenuPresenter
import com.sf0404.common.container.activity.BackPressCallback
import com.sf0404.common.container.activity.BaseActivity
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.welcome_menu_fragment.*
import javax.inject.Inject


class WelcomeMenuFragment : BasePresenterInjectionFragment<WelcomeMenuPresenter>(), WelcomeMenuView, BackPressCallback {
    @Inject
    lateinit var viewPresenter: WelcomeMenuPresenter

    override fun getPresenter() = viewPresenter

    override fun onHandleBackPress(): Boolean {
        NavigateUtil.logout(activity!!)
        return true
    }

    companion object {
        fun showMe(activity: Activity?) {
            val intentBuilder = ContainerActivity.IntentBuilder(activity)
            intentBuilder.setFragmentClass(WelcomeMenuFragment::class.java)
                    .setActionMode(ToolbarMode.NONE)
            intentBuilder.start()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.welcome_menu_fragment
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val act = activity
        if (act is BaseActivity) {
            act.registerBackPress(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCollection.setOnClickListener {
            activity?.showFragment<ClientSignInFragment>()
        }
    }
}
