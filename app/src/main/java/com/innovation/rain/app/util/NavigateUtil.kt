package com.innovation.rain.app.util

import android.app.Activity
import com.innovation.rain.R
import com.innovation.rain.feature.agentlogin.view.AgentLoginFragment
import com.innovation.rain.feature.welcomemenu.view.WelcomeMenuFragment

class NavigateUtil {
    companion object {
        fun logout(activity: Activity) {
            // TODO goto launcher Need confirmation
            AgentLoginFragment.showMe(activity)
            activity.finishAffinity()
            activity.overridePendingTransition(R.anim.anim_activity_next, R.anim.anim_activity_previous)
        }

        fun goToWelcomeMenu(activity: Activity) {
            WelcomeMenuFragment.showMe(activity)
            activity.finishAffinity()
        }
    }
}
