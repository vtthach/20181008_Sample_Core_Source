package com.innovation.rain.feature.agentlogout

import android.app.IntentService
import android.content.Intent
import com.innovation.rain.app.injection.module.model.AppBus
import com.innovation.rain.app.utils.buildHeader
import com.innovation.rain.feature.agentlogout.business.model.AgentLogoutParam
import com.innovation.rain.feature.agentlogout.business.repository.AgentLogoutRepository
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class AgentLogoutIntentService : IntentService("AgentLogoutIntentService") {

    @Inject
    lateinit var repository: AgentLogoutRepository

    @Inject
    lateinit var appBus: AppBus

    private var disposables = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()
        AndroidInjection.inject(this)
    }

    override fun onHandleIntent(intent: Intent?) {
        val param = AgentLogoutParam().apply {
            header = buildHeader(appBus.sessionId)
        }
        disposables.add(repository.agentLogout(param)
                .doOnTerminate { appBus.resetSessionId() }
                .subscribe({
                    Timber.i("Logout success")
                }, {
                    Timber.e("Logout failed")
                }))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
        Timber.i("AgentLogoutIntentService is destroyed")
    }

    private fun AppBus.resetSessionId() {
        this.sessionId = ""
    }
}