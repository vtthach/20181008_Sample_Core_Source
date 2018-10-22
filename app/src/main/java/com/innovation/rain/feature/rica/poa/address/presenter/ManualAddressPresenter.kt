package com.innovation.rain.feature.rica.poa.address.presenter

import com.sf0404.core.application.base.presenter.BasePresenter

interface ManualAddressPresenter : BasePresenter {
    fun validate(address: String, streetAddress: String, suburb: String, town: String, province: String)
    fun submitManualAddress()
}