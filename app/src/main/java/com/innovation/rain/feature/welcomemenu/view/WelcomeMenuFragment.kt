package com.innovation.rain.feature.welcomemenu.view

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.feature.collection.signin.view.ClientSignInFragment
import com.innovation.rain.feature.welcomemenu.presenter.WelcomeMenuPresenter
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode
import com.sf0404.core.application.business.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.welcome_menu_fragment.*
import javax.inject.Inject


class WelcomeMenuFragment : BasePresenterInjectionFragment<WelcomeMenuPresenter>(), WelcomeMenuView {

    @Inject
    lateinit var viewPresenter: WelcomeMenuPresenter

    override fun getPresenter() = viewPresenter

    companion object {
        fun showMe(activity: FragmentActivity?) {
            val intentBuilder = ContainerActivity.IntentBuilder(activity)
            intentBuilder.setFragmentClass(WelcomeMenuFragment::class.java)
                    .setActionMode(ToolbarMode.NONE)
            intentBuilder.start()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.welcome_menu_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCollection.setOnClickListener {
            ClientSignInFragment.showMe(activity)
        }
    }
}
