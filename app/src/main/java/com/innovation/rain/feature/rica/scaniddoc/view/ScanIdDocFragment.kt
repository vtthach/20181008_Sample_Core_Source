package com.innovation.rain.feature.rica.scaniddoc.view

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.enums.RicaState
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.scaniddoc.presenter.ScanIdDocPresenter
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
        ricaState = RicaState.STATE_LOADED
        btScan.setOnClickListener{ricaState = RicaState.STATE_DONE}
    }
}