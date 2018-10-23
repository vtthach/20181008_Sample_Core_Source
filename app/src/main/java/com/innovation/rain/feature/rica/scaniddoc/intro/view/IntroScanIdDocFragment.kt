package com.innovation.rain.feature.rica.scaniddoc.intro.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.innovation.rain.R
import com.innovation.rain.app.utils.showFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.idbook.view.ScanIdBookFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.view.ScanIdCardFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.passport.view.ScanPassportFragment
import com.sf0404.core.application.base.fragment.BaseFragment
import kotlinx.android.synthetic.main.intro_scan_id_doc_fragment.*


class IntroScanIdDocFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.intro_scan_id_doc_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnIdBook.run {
            setOnClickListener {
                showFragmentAndFinish<ScanIdBookFragment>()
            }
            isActivated = true
        }

        btnIdCard.run {
            setOnClickListener {
                showFragmentAndFinish<ScanIdCardFragment>()
            }
            isActivated = true
        }

        btnPassport.run {
            setOnClickListener {
                showFragmentAndFinish<ScanPassportFragment>()
            }
            isActivated = true
        }
    }

    private inline fun <reified T : Fragment> showFragmentAndFinish() {
        showFragment<T>(flag = Intent.FLAG_ACTIVITY_FORWARD_RESULT)
        finish()
    }
}
