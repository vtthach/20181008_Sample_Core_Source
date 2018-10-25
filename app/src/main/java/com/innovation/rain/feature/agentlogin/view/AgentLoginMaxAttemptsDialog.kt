package com.innovation.rain.feature.agentlogin.view

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.sf0404.common.dialog.fragment.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_max_attempts_login.*

class AgentLoginMaxAttemptsDialog : BaseDialogFragment() {

    override fun getLayoutId(): Int {
        return R.layout.dialog_max_attempts_login
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnOk.setOnClickListener {
            activity?.finishAffinity()
        }
    }
}