package com.innovation.rain.app.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.sf0404.common.container.activity.ContainerActivity
import com.sf0404.common.container.mode.ToolbarMode

/**
 * Created by AnhVu on 12-Oct-2018.
 */

inline fun <reified T : Fragment> FragmentActivity.showFragment(flag: Int? = null) {
    val intentBuilder = ContainerActivity.IntentBuilder(this)
    flag?.let {
        intentBuilder.setFlag(flag)
    }
    intentBuilder.setFragmentClass(T::class.java)
            .setActionMode(ToolbarMode.NONE)
            .start()
}

inline fun <reified T : Fragment> Fragment.showFragmentWithRequest(requestCode: Int = ContainerActivity.UNUSED_VALUE) {
    val intentBuilder = ContainerActivity.IntentBuilder(this)
    intentBuilder.setFragmentClass(T::class.java)
            .setRequestCode(requestCode)
            .start()
}

inline fun <reified T : Fragment> Fragment.showFragmentWithFlag(flag: Int = ContainerActivity.UNUSED_VALUE) {
    val intentBuilder = ContainerActivity.IntentBuilder(this)
    intentBuilder.setFragmentClass(T::class.java)
            .setFlag(flag)
            .setRequestCode(ContainerActivity.UNUSED_VALUE)
            .start()
}