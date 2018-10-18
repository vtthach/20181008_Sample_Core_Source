package com.innovation.rain.feature.rica.address.presenter


import com.innovation.rain.feature.rica.address.view.ManualAddressView
import com.sf0404.core.application.base.presenter.BasePresenterImpl

import javax.inject.Inject


class ManualAddressPresenterImpl @Inject
constructor(view: ManualAddressView) : BasePresenterImpl<ManualAddressView>(view), ManualAddressPresenter {
    override fun validate(address: String, streetAddress: String, suburb: String, town: String, province: String) {
    }

}
