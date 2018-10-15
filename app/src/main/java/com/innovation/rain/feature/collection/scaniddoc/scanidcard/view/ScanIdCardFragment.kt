package com.innovation.rain.feature.collection.scaniddoc.scanidcard.view

import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.feature.collection.scaniddoc.scanidcard.presenter.ScanIdCardPresenter
import javax.inject.Inject


class ScanIdCardFragment : BasePresenterInjectionFragment<ScanIdCardPresenter>(), ScanIdCardView {

    @Inject
    lateinit var mPresenter: ScanIdCardPresenter

    override fun getPresenter() = mPresenter

    override fun getLayoutId(): Int {
        return R.layout.scan_id_book_fragment
    }
}
