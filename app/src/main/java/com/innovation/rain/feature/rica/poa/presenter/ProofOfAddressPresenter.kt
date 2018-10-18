package com.innovation.rain.feature.rica.poa.presenter

import com.innovation.rain.app.base.presenter.BasePresenter

interface ProofOfAddressPresenter : BasePresenter {
    fun handleProceedButtonClicked()
    fun loadAddress()
    fun handleItemSelected(value: Int)
}