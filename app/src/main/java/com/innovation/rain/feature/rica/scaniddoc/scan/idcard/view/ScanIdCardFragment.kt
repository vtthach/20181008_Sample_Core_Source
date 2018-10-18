package com.innovation.rain.feature.rica.scaniddoc.scan.idcard.view

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.feature.rica.home.view.RicaHomeFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.presenter.ScanIdCardPresenter
import kotlinx.android.synthetic.main.scan_id_fragment.*
import javax.inject.Inject


class ScanIdCardFragment : BasePresenterInjectionFragment<ScanIdCardPresenter>(), ScanIdCardView {

    @Inject
    lateinit var mPresenter: ScanIdCardPresenter

    override fun getPresenter() = mPresenter

    override fun getLayoutId() = R.layout.scan_id_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.setTextureView(viewTexture)
        btCapture.setOnClickListener { mPresenter.capture() }
    }

    override fun showSuccessScreen() {
        RicaHomeFragment.showMe(activity, RicaHomeFragment.DONE_STEP_1)
    }

    override fun showFlipCardMessage() {
        showToastInfo("Please flip your card")
    }
}
