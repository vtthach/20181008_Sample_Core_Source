package com.innovation.rain.feature.collection.scaniddoc.scanidcard.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.feature.collection.scaniddoc.scanidcard.presenter.ScanIdCardPresenter
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
        Toast.makeText(context, "Captured", Toast.LENGTH_SHORT).show()
    }

    override fun showFlipCardMessage() {
        Toast.makeText(context, "Please flip your card", Toast.LENGTH_SHORT).show()
    }
}
