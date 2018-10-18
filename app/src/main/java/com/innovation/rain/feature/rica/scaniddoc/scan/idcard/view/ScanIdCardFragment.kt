package com.innovation.rain.feature.rica.scaniddoc.scan.idcard.view

import com.innovation.rain.feature.rica.scaniddoc.scan.common.base.view.BaseScanIdDocFragment
import com.innovation.rain.feature.rica.scaniddoc.scan.idcard.presenter.ScanIdCardPresenter

class ScanIdCardFragment : BaseScanIdDocFragment<ScanIdCardPresenter>(), ScanIdCardView {
    override fun showFlipCardMessage() {
        showToastInfo("Please flip your card")
    }
}
