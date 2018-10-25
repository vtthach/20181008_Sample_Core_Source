package com.innovation.rain.app.dialog

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.feature.agentlogout.AgentLogoutIntentService
import com.sf0404.common.dialog.fragment.BaseDialogFragment
import kotlinx.android.synthetic.main.dialog_exit_confirm.*

/**
 * Created by AnhVu on 22-Oct-2018.
 */
class ExitDialogFragment : BaseDialogFragment() {

    var positiveCallback: (() -> Unit)? = null

    override fun getLayoutId(): Int {
        return R.layout.dialog_exit_confirm
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        negativeBtn.setOnClickListener {
            dismiss()
        }

        positiveBtn.setOnClickListener {
            context?.startService(Intent(activity, AgentLogoutIntentService::class.java))

            if (positiveCallback != null) {
                positiveCallback!!.invoke()
            } else {
                //finish current activity by default
                activity!!.finish()
            }
        }

    }
}