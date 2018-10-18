package com.innovation.rain.app.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode


inline fun <reified T : Fragment> FragmentActivity.showFragment(flag: Int? = null) {
    val intentBuilder = ContainerActivity.IntentBuilder(this)
    flag?.let {
        intentBuilder.setFlag(flag)
    }
    intentBuilder.setFragmentClass(T::class.java)
            .setActionMode(ToolbarMode.NONE)
            .start()
}

inline fun <reified T : Fragment> Fragment.showFragment(flag: Int = ContainerActivity.UNUSED_VALUE,
                                                        requestCode: Int = ContainerActivity.UNUSED_VALUE) {
    val intentBuilder = ContainerActivity.IntentBuilder(this)
    intentBuilder.setFragmentClass(T::class.java)
            .setFlag(flag)
            .setRequestCode(requestCode)
            .start()
}