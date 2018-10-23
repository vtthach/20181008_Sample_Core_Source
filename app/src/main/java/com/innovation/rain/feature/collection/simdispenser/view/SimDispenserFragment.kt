package com.innovation.rain.feature.collection.signin.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.util.NavigateUtil
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.collection.orders.view.OrderListFragment
import com.innovation.rain.feature.collection.signin.view.exception.SimDispenserErrorFragment
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseUiModel
import com.innovation.rain.feature.collection.simdispenser.presenter.SimDispenserPresenter
import com.innovation.rain.feature.collection.simdispenser.view.SimDispenserView
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.collection_client_login_fragment.*
import javax.inject.Inject


class SimDispenserFragment : BasePresenterInjectionFragment<SimDispenserPresenter>(), SimDispenserView {

    @Inject
    lateinit var viewPresenter: SimDispenserPresenter

    override fun getPresenter() = viewPresenter

    override fun showDialogDispensingFail(apiCode: String) {
        SimDispenserErrorFragment.newInstance(apiCode).show(fragmentManager, SimDispenserErrorFragment::class.java.simpleName)
    }

    override fun getLayoutId() = R.layout.fragment_collection_dispensing

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnExit.setOnClickListener {
            NavigateUtil.logout(this.activity!!)
        }
    }
}
