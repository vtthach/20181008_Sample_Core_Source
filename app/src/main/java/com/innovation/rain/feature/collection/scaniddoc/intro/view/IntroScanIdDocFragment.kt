package com.innovation.rain.feature.collection.scaniddoc.intro.view

import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BaseFragment
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.collection.scaniddoc.scanidbook.view.ScanIdBookFragment
import com.innovation.rain.feature.collection.scaniddoc.scanidcard.view.ScanIdCardFragment
import kotlinx.android.synthetic.main.intro_scan_id_doc_fragment.*


class IntroScanIdDocFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.intro_scan_id_doc_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnIdBook.setOnClickListener {
            activity?.showFragment<ScanIdBookFragment>()
        }

        btnIdCard.setOnClickListener {
            activity?.showFragment<ScanIdCardFragment>()
        }
    }
}
