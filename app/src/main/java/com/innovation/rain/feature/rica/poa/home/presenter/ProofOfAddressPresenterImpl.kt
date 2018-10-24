package com.innovation.rain.feature.rica.poa.home.presenter

import android.os.Bundle
import com.innovation.rain.feature.rica.poa.home.view.ProofOfAddressView
import com.sf0404.core.application.base.presenter.BasePresenterImpl
import javax.inject.Inject


class ProofOfAddressPresenterImpl @Inject
constructor(view: ProofOfAddressView) : BasePresenterImpl<ProofOfAddressView>(view), ProofOfAddressPresenter {

    private var selectedPos: Int? = null
    private var addressList: MutableList<String>? = null

    override fun handleItemSelected(value: Int) {
        selectedPos = value
    }

    override fun onViewCreated(savedInstanceState: Bundle?, arguments: Bundle?) {
        super.onViewCreated(savedInstanceState, arguments)
        loadAddress()
    }

    override fun loadAddress() {
        addressList = getAddress()
        if (addressList != null && !addressList!!.isEmpty()) {
            view.showAddressList(addressList!!)
        } else {
            view.showManualCaptureBtn()
        }

    }

    private fun getAddress(): MutableList<String>? {
        return mutableListOf("000, Street, Suburb, City",
                "000, Street, Suburb, City",
                "000, Street, Suburb, City",
                "000, Street, Suburb, City",
                "000, Street, Suburb, City",
                "Manually Capture")
    }

    override fun handleProceedButtonClicked() {
        if (addressList?.isEmpty() != true && selectedPos == addressList!!.size - 1) {
            view.showCaptureManualScreen()
        } else {
            //Call api to capture address
            view.onDone()
        }
    }


}
