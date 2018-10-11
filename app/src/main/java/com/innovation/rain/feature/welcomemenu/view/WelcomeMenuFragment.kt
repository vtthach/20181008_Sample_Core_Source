package com.innovation.rain.feature.welcomemenu.view

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.common.fragment.BasePresenterInjectionFragment
import com.innovation.rain.feature.welcomemenu.presenter.WelcomeMenuPresenter
import com.innovation.rain.feature.welcomemenu.presenter.WelcomeMenuPresenterImpl
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode
import javax.inject.Inject


class WelcomeMenuFragment : BasePresenterInjectionFragment<WelcomeMenuPresenter>(), WelcomeMenuView {

    @Inject
    lateinit var presenter: WelcomeMenuPresenterImpl

    override fun getPresenter(): WelcomeMenuPresenter {
        return presenter
    }

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
    }
}
