package com.innovation.rain.feature.rica.poa.address.presenter


import com.innovation.rain.feature.rica.poa.address.model.Address
import com.innovation.rain.feature.rica.poa.address.view.ManualAddressView
import com.sf0404.core.application.base.presenter.BasePresenterImpl
import javax.inject.Inject


class ManualAddressPresenterImpl @Inject
constructor(view: ManualAddressView) : BasePresenterImpl<ManualAddressView>(view), ManualAddressPresenter {
    private lateinit var address: String
    private lateinit var streetAddress: String
    private lateinit var suburb: String
    private lateinit var town: String
    private lateinit var province: String

    override fun validate(address: String, streetAddress: String, suburb: String, town: String, province: String) {
        val valid = streetAddress.isNotEmpty() && suburb.isNotEmpty() && town.isNotEmpty() && province.isNotEmpty()
        if (valid) {
            this.address = address
            this.streetAddress = streetAddress
            this.suburb = suburb
            this.town = town
            this.province = province
        }
        view.enableOkButton(valid)
    }

    override fun submitManualAddress() {
        val address = Address(address, streetAddress, suburb, town, province)
        //TODO: Submit address here
        view.gotoScanPOAScreen()
    }
}
