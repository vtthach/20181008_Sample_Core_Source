package com.innovation.rain.feature.rica.poa.presenter

import com.innovation.rain.feature.rica.address.model.Address
import com.sf0404.core.application.base.presenter.BasePresenter

interface ProofOfAddressPresenter : BasePresenter {
    fun handleProceedButtonClicked()
    fun loadAddress()
    fun handleItemSelected(value: Int)
    fun submitManualAddress(address: Address)
}