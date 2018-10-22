package  com.innovation.rain.feature.rica.poa.address.view

import com.sf0404.core.application.base.presenter.BaseView

interface ManualAddressView : BaseView {
    fun enableOkButton(enable: Boolean)

    fun gotoScanPOAScreen()
}
