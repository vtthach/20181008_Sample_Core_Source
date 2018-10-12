package com.innovation.rain.app.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode

/**
 * Created by AnhVu on 12-Oct-2018.
 */

inline fun <reified T : Fragment> FragmentActivity.showFragment(toolbarMode: Int = ToolbarMode.NONE) {
    val intentBuilder = ContainerActivity.IntentBuilder(this)
    intentBuilder.setFragmentClass(T::class.java)
            .setActionMode(toolbarMode)
            .start()
}