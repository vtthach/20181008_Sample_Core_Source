package com.innovation.rain.feature.rica.address.presenter

import com.innovation.rain.app.base.presenter.BasePresenter

interface ManualAddressPresenter : BasePresenter {
    fun validate(address: String, streetAddress: String, suburb: String, town: String, province: String)
}