package com.innovation.rain.app.utils

import android.app.Activity
import android.content.Intent
import android.support.v4.app.FragmentActivity
import com.innovation.rain.R
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.agentlogin.view.AgentLoginFragment
import com.innovation.rain.feature.welcomemenu.view.WelcomeMenuFragment

class NavigateUtil {
    companion object {
        fun logout(activity: Activity) {
            // TODO goto launcher Need confirmation
            (activity as? FragmentActivity)?.showFragment<AgentLoginFragment>(flag = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            activity.finishAffinity()
            activity.overridePendingTransition(R.anim.anim_activity_next, R.anim.anim_activity_previous)
        }

        fun goToWelcomeMenu(activity: Activity) {
            (activity as? FragmentActivity)?.showFragment<WelcomeMenuFragment>()
            activity.finishAffinity()
        }
    }
}
