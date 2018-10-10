package com.sf0404.sample.cba.circlewithcheck

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.common.fragment.BaseFragment
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode
import kotlinx.android.synthetic.main.agent_login_fragment.*


class AgentLoginFragment : BaseFragment() {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnLogin.isEnabled = false
    }
}
