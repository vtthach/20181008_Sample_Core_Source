package com.innovation.rain.feature.agentlogin.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.MyApplication
import com.innovation.rain.app.utils.showExitDialog
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.agentlogin.presenter.AgentLoginPresenter
import com.innovation.rain.feature.collection.orders.model.OrderEntity
import com.innovation.rain.feature.collection.signin.view.SimDispenserFragment
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

    private val MY_PERMISSIONS_REQUEST = 123

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edPassword.addTextChangedListener(textPwChangeListener)
        edUserId.addTextChangedListener(textUserIdChangeListener)
        btnExit.setOnClickListener {
            fragmentManager?.showExitDialog()
        }
        btnLogin.setOnClickListener {
//            activity?.showFragment<WelcomeMenuFragment>()
            val orderList = listOf(OrderEntity("Sim 1", "No spend limit set as pay as you use "),
                    OrderEntity("Sim 2", "No spend limit set as pay as you use "),
//                OrderEntity("Sim 3", "No spend limit set as pay as you use "),
//                OrderEntity("Sim 4", "No spend limit set as pay as you use "),
//                OrderEntity("Sim 5", "No spend limit set as pay as you use "),
//                OrderEntity("Sim 6", "No spend limit set as pay as you use "),
//                OrderEntity("Sim 7", "No spend limit set as pay as you use "),
//                OrderEntity("Sim 8", "No spend limit set as pay as you use "),
                    OrderEntity("Sim 9", "No spend limit set as pay as you use ")
            )
            MyApplication.getAppComponent().appBus().orderList = orderList
            showFragment<SimDispenserFragment>()
        }

        //TODO remove following code when use knox
        if (ContextCompat.checkSelfPermission(activity!!,
                        Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity!!,
                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    MY_PERMISSIONS_REQUEST)
        }
    }
}
