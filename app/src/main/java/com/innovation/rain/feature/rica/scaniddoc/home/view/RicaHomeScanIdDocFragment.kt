package com.innovation.rain.feature.rica.scaniddoc.home.view

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.enums.RicaState
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.rica.base.BaseRicaFragment
import com.innovation.rain.feature.rica.home.view.RicaHomeFragment
import com.innovation.rain.feature.rica.scaniddoc.home.presenter.RicaHomeScanIdDocPresenter
import com.innovation.rain.feature.rica.scaniddoc.intro.view.IntroScanIdDocFragment
import kotlinx.android.synthetic.main.fragment_home_scan_id_doc.*
import javax.inject.Inject

class RicaHomeScanIdDocFragment : BaseRicaFragment<RicaHomeScanIdDocPresenter>(), RicaHomeScanIdDocView {

    @Inject
    lateinit var mPresenter: RicaHomeScanIdDocPresenter

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

        rootView.setOnClickListener {
            showFragment<IntroScanIdDocFragment>(requestCode = RicaHomeFragment.REQUEST_CODE)
        }
    }
}