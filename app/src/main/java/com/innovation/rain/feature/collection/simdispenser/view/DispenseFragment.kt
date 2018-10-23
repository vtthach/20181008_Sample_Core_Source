package com.innovation.rain.feature.collection.signin.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.util.NavigateUtil
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.collection.orders.view.OrderListFragment
import com.innovation.rain.feature.collection.signin.view.exception.DispenseErrorFragment
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseUiModel
import com.innovation.rain.feature.collection.simdispenser.presenter.DispensePresenter
import com.innovation.rain.feature.collection.simdispenser.view.DispenseView
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.collection_client_login_fragment.*
import javax.inject.Inject


class DispenseFragment : BasePresenterInjectionFragment<DispensePresenter>(), DispenseView {

    @Inject
    lateinit var viewPresenter: DispensePresenter

    override fun getPresenter() = viewPresenter

    override fun notifyNonRegisterId(apiCode: String?) {
        showToastError("Please enter a valid South African ID number [$apiCode]")
    }

    override fun showDialogNoOrder(apiCode: String) {
        DispenseErrorFragment.newInstance(apiCode).show(fragmentManager, DispenseErrorFragment::class.java.simpleName)
    }

    override fun notifyIdInvalid() {
        showToastError("Please enter your South African ID number")
    }

    override fun goToCollectionOrder(info: DispenseUiModel?) {
        activity?.showFragment<OrderListFragment>()
    }

    override fun getLayoutId() = R.layout.fragment_collection_dispensing

    override fun enableButtonProceed(enable: Boolean) {
        btnProceed.isEnabled = enable
        btnProceed.isActivated = enable
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnExit.setOnClickListener {
            NavigateUtil.logout(this.activity!!)
        }

        edId.addTextChangedListener(textIdChangeListener)
        edId.setOnEditorActionListener { _, _, _ -> presenter.onEditorAction(edId.text.toString()) }
        btnProceed.setOnClickListener {
            presenter.proceed(edId.text.toString())
        }
    }

    private val textIdChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable) {
            presenter.onTextIdChanged(s.toString())
        }
    }
}
