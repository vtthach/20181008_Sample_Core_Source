package com.innovation.rain.feature.welcomemenu.view

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.collection.signin.view.ClientSignInFragment
import com.innovation.rain.feature.welcomemenu.presenter.WelcomeMenuPresenter
import kotlinx.android.synthetic.main.welcome_menu_fragment.*
import javax.inject.Inject


class WelcomeMenuFragment : BasePresenterInjectionFragment<WelcomeMenuPresenter>(), WelcomeMenuView {

    @Inject
    lateinit var viewPresenter: WelcomeMenuPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.welcome_menu_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCollection.setOnClickListener {
            activity?.showFragment<ClientSignInFragment>()
        }
    }
}
