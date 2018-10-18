package com.innovation.rain.feature.rica.scaniddoc.intro.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.base.fragment.BaseFragment
import com.innovation.rain.app.utils.showFragmentWithFlag
import com.innovation.rain.feature.rica.scaniddoc.scan.idbook.view.ScanIdBookFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.view.ScanIdCardFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.passport.view.ScanPassportFragment
import kotlinx.android.synthetic.main.intro_scan_id_doc_fragment.*


class IntroScanIdDocFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.intro_scan_id_doc_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnIdBook.setOnClickListener {
            showFragmentWithFlag<ScanIdBookFragment>(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
            finish()
        }

        btnIdCard.setOnClickListener {
            showFragmentWithFlag<ScanIdCardFragment>(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
            finish()
        }

        btnPassport.setOnClickListener {
            showFragmentWithFlag<ScanPassportFragment>(Intent.FLAG_ACTIVITY_FORWARD_RESULT)
            finish()
        }
    }
}
