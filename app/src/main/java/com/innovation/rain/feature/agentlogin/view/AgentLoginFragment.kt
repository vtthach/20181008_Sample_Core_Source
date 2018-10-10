package com.innovation.rain.feature.agentlogin.view

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.common.fragment.BasePresenterInjectionFragment
import com.innovation.rain.feature.agentlogin.presenter.AgentLoginPresenter
import com.innovation.rain.feature.agentlogin.presenter.AgentLoginPresenterImpl
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode
import kotlinx.android.synthetic.main.agent_login_fragment.*
import javax.inject.Inject


class AgentLoginFragment : BasePresenterInjectionFragment<AgentLoginPresenter>(), AgentLoginView {
    override fun notifyEmptyUserId() {
    }

    override fun enableButtonSignIn(allowEnableSignInButton: Boolean) {
        btnLogin.isEnabled = allowEnableSignInButton
    }

    override fun notifyEmptyPw() {
    }

    @Inject
    lateinit var presenter: AgentLoginPresenterImpl

    override fun getPresenter(): AgentLoginPresenter {
        return presenter
    }

    companion object {
        fun showMe(activity: Activity) {
            val intentBuilder = ContainerActivity.IntentBuilder(activity)
            intentBuilder.setFragmentClass(AgentLoginFragment::class.java)
                    .setActionMode(ToolbarMode.NONE)
            intentBuilder.start()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.agent_login_fragment
    }

    private val textPwChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable) {
            presenter.onTextPasswordChanged(s.toString())
        }
    }
    private val textUserIdChangeListener: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable) {
            presenter.onTextUserIdChanged(s.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edPassword.addTextChangedListener(textPwChangeListener)
        edUserId.addTextChangedListener(textUserIdChangeListener)
    }
}
