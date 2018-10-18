package  com.innovation.rain.feature.rica.poa.view

import com.sf0404.core.application.base.presenter.BasePresenterView


interface ProofOfAddressView : BasePresenterView {
    fun showAddressList(list: List<String>)
    fun showManualCaptureBtn()
    fun showCaptureManualScreen()
}
