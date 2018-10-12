package com.innovation.rain.feature.agentlogin.view

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.feature.agentlogin.presenter.AgentLoginPresenter
import com.innovation.rain.feature.welcomemenu.view.WelcomeMenuFragment
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode
import com.sf0404.core.application.base.fragment.BasePresenterInjectionFragment
import kotlinx.android.synthetic.main.agent_login_fragment.*
import javax.inject.Inject


class AgentLoginFragment : BasePresenterInjectionFragment<AgentLoginPresenter>(), AgentLoginView {

    @Inject
    lateinit var viewPresenter: AgentLoginPresenter

    override fun getPresenter() = viewPresenter

    override fun enableButtonSignIn(allowEnableSignInButton: Boolean) {
        btnLogin.isEnabled = allowEnableSignInButton
        btnLogin.isActivated = allowEnableSignInButton
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
        btnExit.setOnClickListener {
            activity?.finish()
        }
        btnLogin.setOnClickListener {
            WelcomeMenuFragment.showMe(activity)
        }
    }
}
