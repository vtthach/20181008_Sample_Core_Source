package com.innovation.rain.feature.collection.signin.view

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInUiModel
import com.innovation.rain.feature.collection.signin.presenter.ClientSignInPresenter
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
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

    override fun notifyIdInvalid() {
        showToastError("Please enter your South African ID number.")
    }

    override fun goToCollectionOrder(info: ClientSignInUiModel?) {
        // TODO integrate @JAV here
        showToastInfo("Ok -> Goto Order")
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
            activity?.finish()
        }

        edId.addTextChangedListener(textIdChangeListener)

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
