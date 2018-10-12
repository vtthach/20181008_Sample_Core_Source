package com.innovation.rain

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.innovation.rain.feature.agentlogin.view.AgentLoginFragment

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AgentLoginFragment.showMe(this)
        finish()
    }
}
