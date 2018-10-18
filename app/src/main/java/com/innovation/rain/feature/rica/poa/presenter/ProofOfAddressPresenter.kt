package com.innovation.rain.feature.rica.poa.presenter

import com.sf0404.core.application.base.presenter.BasePresenter

interface ProofOfAddressPresenter : BasePresenter {
    fun handleProceedButtonClicked()
    fun loadAddress()
    fun handleItemSelected(value: Int)
}