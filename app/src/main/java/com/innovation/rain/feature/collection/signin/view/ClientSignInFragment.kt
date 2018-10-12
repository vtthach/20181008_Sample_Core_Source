package com.innovation.rain.feature.collection.signin.view

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.feature.collection.signin.presenter.ClientSignInPresenter
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode
import com.sf0404.core.application.business.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.collection_client_login_fragment.*
import javax.inject.Inject


class ClientSignInFragment : BasePresenterInjectionFragment<ClientSignInPresenter>(), ClientSignInView {

    @Inject
    lateinit var viewPresenter: ClientSignInPresenter

    override fun getPresenter() = viewPresenter

    companion object {
        fun showMe(activity: FragmentActivity?) {
            val intentBuilder = ContainerActivity.IntentBuilder(activity)
            intentBuilder.setFragmentClass(ClientSignInFragment::class.java)
                    .setActionMode(ToolbarMode.NONE)
            intentBuilder.start()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.collection_client_login_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnExit.setOnClickListener {
            activity?.finish()
        }

        btnProceed.setOnClickListener {
            ClientSignInFragment.showMe(activity)
        }
    }
}
