package  com.innovation.rain.feature.rica.poa.view

import com.innovation.rain.app.base.presenter.BasePresenterView

interface ProofOfAddressView : BasePresenterView {
    fun showAddressList(list: List<String>)
    fun showManualCaptureBtn()
    fun showCaptureManualScreen()
}
