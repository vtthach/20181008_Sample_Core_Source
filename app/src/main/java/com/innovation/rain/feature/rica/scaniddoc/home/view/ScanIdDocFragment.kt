package com.innovation.rain.feature.rica.scaniddoc.home.view

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.enums.RicaState
import com.innovation.rain.app.utils.showFragmentWithRequest
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.home.view.RicaHomeFragment
import com.innovation.rain.feature.rica.scaniddoc.home.presenter.ScanIdDocPresenter
import com.innovation.rain.feature.rica.scaniddoc.intro.view.IntroScanIdDocFragment
import kotlinx.android.synthetic.main.fragment_home_scan_id_doc.*
import javax.inject.Inject

class ScanIdDocFragment : BaseRicaFragment<ScanIdDocPresenter>(), ScanIdDocView {

    @Inject
    lateinit var mPresenter: ScanIdDocPresenter

    override fun getPreLoadStateTitle() = ""

    override fun getDoneStateTitle() = getString(R.string.id_verified)

    override fun getLoadedStateLayout() = R.layout.fragment_home_scan_id_doc

    override fun getPresenter() = mPresenter

    override fun onProceedButtonClicked() {
        //TODO: NTL
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stateId = arguments?.getInt(RicaHomeFragment.BUNDLE_KEY_RICA_STATE, RicaState.STATE_LOADED.id)
        ricaState = RicaState.valueOf( stateId ?: RicaState.STATE_LOADED.id)

        rootView.setOnClickListener{
            showFragmentWithRequest<IntroScanIdDocFragment>(RicaHomeFragment.REQUEST_CODE)
        }
    }
}