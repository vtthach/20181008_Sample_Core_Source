package com.innovation.rain.feature.rica.scaniddoc.scan.idbook.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.idbook.presenter.ScanIdBookPresenter
import kotlinx.android.synthetic.main.scan_id_fragment.*
import javax.inject.Inject


class ScanIdBookFragment : BasePresenterInjectionFragment<ScanIdBookPresenter>(), ScanIdBookView {

    @Inject
    lateinit var mPresenter: ScanIdBookPresenter

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
}
