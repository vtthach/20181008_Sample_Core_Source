package com.innovation.rain.feature.collection.signin.view

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.util.NavigateUtil
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.collection.orders.view.OrderListFragment
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInUiModel
import com.innovation.rain.feature.collection.signin.presenter.ClientSignInPresenter
import com.innovation.rain.feature.collection.signin.view.exception.NoOrderFragment
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.collection_client_login_fragment.*
import javax.inject.Inject


class ClientSignInFragment : BasePresenterInjectionFragment<ClientSignInPresenter>(), ClientSignInView {

    @Inject
    lateinit var viewPresenter: ClientSignInPresenter

    companion object {
        fun showMe(activity: FragmentActivity?) {
            val intentBuilder = ContainerActivity.IntentBuilder(activity)
            intentBuilder.setFragmentClass(ClientSignInFragment::class.java)
                    .setActionMode(ToolbarMode.NONE)
            intentBuilder.start()
        }
    }

    override fun getPresenter() = viewPresenter

    override fun notifyNonRegisterId(apiCode: String?) {
        showToastError("Invalid ID [$apiCode]")
    }

    override fun showDialogNoOrder(apiCode: String) {
        NoOrderFragment.newInstance(apiCode).show(fragmentManager, NoOrderFragment::class.java.simpleName)
    }

    override fun notifyIdInvalid() {
        showToastError("Please enter your South African ID number")
    }

    override fun goToCollectionOrder(info: ClientSignInUiModel?) {
        activity?.showFragment<OrderListFragment>()
    }

    override fun getLayoutId(): Int {
        return R.layout.collection_client_login_fragment
    }

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
        edId.setOnEditorActionListener { v, actionId, event -> presenter.onEditorAction(edId.text.toString()) }
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
