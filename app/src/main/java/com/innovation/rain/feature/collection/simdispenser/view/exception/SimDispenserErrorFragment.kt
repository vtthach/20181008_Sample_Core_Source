package com.innovation.rain.feature.collection.signin.view.exception

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.utils.NavigateUtil
import com.sf0404.common.dialog.fragment.BaseDialogFragment
import com.sf0404.common.fragment.util.BundleUtils
import kotlinx.android.synthetic.main.collection_client_login_no_order_fragment.*


class SimDispenserErrorFragment : BaseDialogFragment() {

    companion object {
        fun newInstance(code: String): SimDispenserErrorFragment {
            val fragment = SimDispenserErrorFragment()
            fragment.arguments = BundleUtils.getStatusCodeBundle(code)
            return fragment
        }
    }

    override fun getLayoutId() = R.layout.collection_client_login_no_order_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btnMainMenu.setOnClickListener {
            NavigateUtil.logout(activity!!)
            dismiss()
        }

        btnWelcomeScreen.setOnClickListener {
            NavigateUtil.goToWelcomeMenu(activity!!)
            dismiss()
        }

        tvApiCode.text = BundleUtils.getStatusCode(arguments)
    }
}
