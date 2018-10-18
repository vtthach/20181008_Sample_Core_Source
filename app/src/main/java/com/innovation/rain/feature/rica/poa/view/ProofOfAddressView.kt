package  com.innovation.rain.feature.rica.poa.view

import com.sf0404.core.application.base.presenter.BaseView


interface ProofOfAddressView : BaseView {
    fun showAddressList(list: List<String>)
    fun showManualCaptureBtn()
    fun showCaptureManualScreen()
}
