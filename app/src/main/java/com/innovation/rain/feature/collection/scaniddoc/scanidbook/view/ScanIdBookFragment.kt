package com.innovation.rain.feature.collection.scaniddoc.scanidbook.view

import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BasePresenterInjectionFragment
import com.innovation.rain.feature.collection.scaniddoc.scanidbook.presenter.ScanIdBookPresenter
import javax.inject.Inject


class ScanIdBookFragment : BasePresenterInjectionFragment<ScanIdBookPresenter>(), ScanIdBookView {

    @Inject
    lateinit var mPresenter: ScanIdBookPresenter

    override fun getPresenter() = mPresenter

    override fun getLayoutId(): Int {
        return R.layout.scan_id_book_fragment
    }
}
