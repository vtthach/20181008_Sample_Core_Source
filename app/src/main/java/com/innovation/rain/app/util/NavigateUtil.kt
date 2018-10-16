package com.innovation.rain.app.util

import android.app.Activity
import com.innovation.rain.feature.agentlogin.view.AgentLoginFragment
import com.innovation.rain.feature.welcomemenu.view.WelcomeMenuFragment

class NavigateUtil {
    companion object {
        fun goToAgentSignIn(activity: Activity) {
            AgentLoginFragment.showMe(activity)
            activity.finishAffinity()
        }

        fun goToWelcomeMenu(activity: Activity) {
            WelcomeMenuFragment.showMe(activity)
            activity.finishAffinity()
        }
    }
}
