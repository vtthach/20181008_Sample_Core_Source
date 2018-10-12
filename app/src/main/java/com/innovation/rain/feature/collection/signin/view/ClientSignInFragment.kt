package com.innovation.rain.feature.collection.signin.view

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.collection.orders.view.OrderListFragment
import com.innovation.rain.feature.collection.signin.presenter.ClientSignInPresenter
import kotlinx.android.synthetic.main.collection_client_login_fragment.*
import javax.inject.Inject


class ClientSignInFragment : BasePresenterInjectionFragment<ClientSignInPresenter>(), ClientSignInView {

    @Inject
    lateinit var viewPresenter: ClientSignInPresenter

    override fun getPresenter() = viewPresenter

    override fun getLayoutId(): Int {
        return R.layout.collection_client_login_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnExit.setOnClickListener {
            activity?.finish()
        }

        btnProceed.setOnClickListener {
            activity?.showFragment<OrderListFragment>()
        }
    }
}
