package com.innovation.rain

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sf0404.sample.cba.circlewithcheck.AgentLoginFragment

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AgentLoginFragment.showMe(this)
        finish()
    }
}
